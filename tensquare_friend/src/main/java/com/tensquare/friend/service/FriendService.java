package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao noFriendDao;
    public int addFriend(String userid,String friendid){
    //先判断userid到friendid是否有数据，有就是重复添加好友返回0
      Friend friend = friendDao.findByUseridAndFriendid(userid,friendid);
    if(friend!=null){
        return 0;
    }
    //直接添加好友，让好友列表中userid到friendid单向的type为0
    friend = new Friend();
    friend.setUserid(userid);
    friend.setFriendid(friendid);
    friend.setIslike("0");
    friendDao.save(friend);
    if(friendDao.findByUseridAndFriendid(friendid,userid)!=null){
        //把双方的islike都改为1
        friendDao.updateIsLike("1",userid,friendid);
        friendDao.updateIsLike("1",friendid,userid);
    }
        return 1;
    //判断从friendid到userid是否有数据，如果有，把双方的状态都改为1
    }

    public int addNoFriend(String userid, String friendid) {
        //先判断是否已经是非好友
        NoFriend noFriend=noFriendDao.findByUseridAndFriendid(userid,friendid);
        if(noFriend!=null){
            return 0;
        }
        noFriend=new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userid, String friendid) {
        //先删除好友表中userid到friendid这条数据
        friendDao.deletefriend(userid,friendid);
        //更新friendid到userid的islike为0
        //非好友表中添加数据
        friendDao.updateIsLike("0",friendid,userid);
        //非好友表中添加数据
        NoFriend nofriend=new NoFriend();
        nofriend.setUserid(userid);
        nofriend.setFriendid(friendid);
        noFriendDao.save(nofriend);
    }
}
