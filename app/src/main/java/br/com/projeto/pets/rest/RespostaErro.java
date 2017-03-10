package br.com.projeto.pets.rest;


/**
 * Created by srolemberg on 18/03/16.
 */
public class RespostaErro {

    private String error;
    private String messageInfo;

    private String success;
    private String responseHeader;
    private String codigoHTTP;


    public RespostaErro() {
    }

    public RespostaErro(String error, String messageInfo, String success, String responseHeader, String codigoHTTP) {
        this.error = error;
        this.messageInfo = messageInfo;
        this.success = success;
        this.responseHeader = responseHeader;
        this.codigoHTTP = codigoHTTP;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getCodigoHTTP() {
        return codigoHTTP;
    }

    public void setCodigoHTTP(String codigoHTTP) {
        this.codigoHTTP = codigoHTTP;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RespostaErro{");
        sb.append("error='").append(error).append('\'');
        sb.append(", messageInfo='").append(messageInfo).append('\'');
        sb.append(", success='").append(success).append('\'');
        sb.append(", responseHeader='").append(responseHeader).append('\'');
        sb.append(", codigoHTTP='").append(codigoHTTP).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
