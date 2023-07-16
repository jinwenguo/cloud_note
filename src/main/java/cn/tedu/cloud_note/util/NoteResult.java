package cn.tedu.cloud_note.util;

import java.io.Serializable;

public class NoteResult<T> implements Serializable {
    private static final long serialVersionUID = -1916574049867514600L;
    private int status=0;
    private String msg="success";
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NoteResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
