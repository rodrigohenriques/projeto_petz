package br.com.projeto.pets.model;

import java.io.Serializable;

/**
 * Created by srolemberg on 13/02/17.
 */

public class Session implements Serializable {

    private String token;
    private String expires;

    private Session() {
    }

    private Session(Builder builder) {
        token = builder.token;
        expires = builder.expires;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Session copy) {
        Builder builder = new Builder();
        builder.token = copy.token;
        builder.expires = copy.expires;
        return builder;
    }

    public String getToken() {
        return token;
    }

    public String getExpires() {
        return expires;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (token != null ? !token.equals(session.token) : session.token != null) return false;
        return expires != null ? expires.equals(session.expires) : session.expires == null;

    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (expires != null ? expires.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Session{" +
                "token='" + token + '\'' +
                ", expires='" + expires + '\'' +
                '}';
    }


    private static final class Builder {
        private String token;
        private String expires;

        private Builder() {
        }

        public Builder withToken(String val) {
            token = val;
            return this;
        }

        public Builder withExpires(String val) {
            expires = val;
            return this;
        }

        public Session build() {
            return new Session(this);
        }
    }
}
