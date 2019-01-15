package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> {
    //联合主键查询
    public Friend findByUseridAndFriendid(String userid,String friendid);
    @Modifying
    @Query(value = "UPDATE tb_friend SET islike=? WHERE userid = ? AND friendid =?",nativeQuery = true)
    public void updateIsLike(String isLike,String userid,String friendid);

    @Modifying
    @Query(value = "delete from tb_friend WHERE userid = ? AND friendid =?",nativeQuery = true)
    void deletefriend(String userid, String friendid);
}
