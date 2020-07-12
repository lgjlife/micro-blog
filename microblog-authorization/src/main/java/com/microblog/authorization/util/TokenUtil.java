package com.microblog.authorization.util;


import org.springframework.security.jwt.codec.Codecs;
import org.springframework.security.jwt.crypto.sign.*;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {

    private PublicKey publicKey =  null;

    private PrivateKey privateKey = null;

    private SignatureVerifier verifier;

    static byte[] PERIOD = Codecs.utf8Encode(".");

    //签名
    private Signer signer;
    private String verifierKey = "11536351315";

    private JsonParser objectMapper = JsonParserFactory.create();



    public void init(){
        KeyPair keyPair = new RsaKeypairCreator().genKeyPair();
        publicKey  = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

        signer = new RsaSigner((RSAPrivateKey)privateKey);
        //signer =  new MacSigner(this.verifierKey);


        verifier = new RsaVerifier((RSAPublicKey)publicKey);


      //  verifier.verify();
    }

    public static void main(String args[]){

        TokenUtil tokenUtil = new TokenUtil();
        tokenUtil.init();
        String jwt = tokenUtil.createJwt();
        System.out.println(jwt);
    }

    public String createJwt(){

        //jwt头部内容
        Map<String,String> headers = new HashMap<>();
        headers.put("alg","RS256");
        headers.put("typ","JWT");
        String headerStr = objectMapper.formatMap(headers);
        byte[] header = Codecs.utf8Encode(headerStr);


        //jwt实体内容
        Map<String,String> content = new HashMap<>();
        content.put("user","libai");
        content.put("exp","155645564");
        String contentStr = objectMapper.formatMap(content);
        byte[] claims = Codecs.utf8Encode(contentStr);

        //签名
        System.out.println("签名............");
        byte[] crypto = signer.sign(
                Codecs.concat(
                        //header
                        new byte[][]{Codecs.b64UrlEncode(header),
                        // .
                        PERIOD,
                        // claims
                        Codecs.b64UrlEncode(claims)}));

        byte[] allByte =  Codecs.concat(
                new byte[][]{Codecs.b64UrlEncode(header),
                        PERIOD,
                        Codecs.b64UrlEncode(claims),
                        PERIOD,
                        Codecs.b64UrlEncode(crypto)});


        String jwt = Codecs.utf8Decode(allByte);


        System.out.println("--------------验证　verifier---------------------");
        verifier.verify(
                Codecs.concat(new byte[][]{Codecs.b64UrlEncode(header),
                PERIOD,
                Codecs.b64UrlEncode(claims)}),crypto);
        System.out.println("--------------verifier---------------------");


        return jwt;

    }
}
