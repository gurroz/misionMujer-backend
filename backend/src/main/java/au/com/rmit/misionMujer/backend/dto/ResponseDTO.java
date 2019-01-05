package au.com.rmit.misionMujer.backend.dto;

import java.util.StringJoiner;

public class ResponseDTO<T> {
    private String result;
    private T data;

    public ResponseDTO(){}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ResponseDTO.class.getSimpleName() + "[", "]")
                .add("result='" + result + "'")
                .add("data='" + data + "'")
                .toString();
    }
}
