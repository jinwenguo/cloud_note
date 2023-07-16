package cn.tedu.cloud_note.dao;

import cn.tedu.cloud_note.entity.Share;

/**
 * @author Administrator
 * @description 针对表【cn_share】的数据库操作Mapper
 * @createDate 2023-07-15 15:39:50
 * @Entity cn.tedu.cloud_note.entity.Share
 */
public interface ShareDao {
    int insertAll(Share share);

   
}




