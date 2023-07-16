package cn.tedu.cloud_note.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName cn_share
 */
@Data
public class Share implements Serializable {
    /**
     * 共享ID
     */
    private String shareId;

    /**
     * 共享标题
     */
    private String shareTitle;

    /**
     * 共享内容
     */
    private String shareBody;

    /**
     * 笔记id
     */
    private String noteId;

    private static final long serialVersionUID = 1L;
}