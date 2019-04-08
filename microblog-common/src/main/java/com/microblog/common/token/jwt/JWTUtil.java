package com.microblog.common.token.jwt;

import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *功能描述
 * @author lgj
 * @Description  jwt 工具类
 * @date 2/27/19
*/
@Slf4j
public class JWTUtil {

    //
    private static RsaJsonWebKey rsaJsonWebKey ;


    static {
        try{

            //
            // JSON Web Token is a compact URL-safe means of representing claims/attributes to be transferred between two parties.
            // This example demonstrates producing and consuming a signed JWT
            //

            // Generate an RSA key pair, which will be used for signing and verification of the JWT, wrapped in a JWK
            rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
            // Give the JWK a Key ID (kid), which is just the polite thing to do
            rsaJsonWebKey.setKeyId("k1");

            log.debug("rsaJsonWebKey = {},{}",rsaJsonWebKey,rsaJsonWebKey.getPrivateKey());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static  String createJwt( Map<String, String> claims){

        String jwt = null;
        // Create the Claims, which will be the content of the JWT
        JwtClaims jwtClaims = new JwtClaims();
        jwtClaims.setIssuer("Issuer");  // who creates the token and signs it
        jwtClaims.setAudience("Audience"); // to whom the token is intended to be sent
        jwtClaims.setExpirationTimeMinutesInTheFuture(1); // time when the token will expire (10 minutes from now)
        jwtClaims.setGeneratedJwtId(); // a unique identifier for the token
        jwtClaims.setIssuedAtToNow();  // when the token was issued/created (now)
        jwtClaims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
        jwtClaims.setSubject("subject"); // the subject/principal is whom the token is about
        claims.forEach((key,value) -> jwtClaims.setClaim(key,value)); // additional claims/attributes about the subject can be added
        List<String> groups = Arrays.asList("group-one", "other-group", "group-three");
        jwtClaims.setStringListClaim("groups", groups); // multi-valued claims work too and will end up as a JSON array



        // A JWT is a JWS and/or a JWE with JSON claims as the payload.
        // In this example it is a JWS so we create a JsonWebSignature object.
        JsonWebSignature jws = new JsonWebSignature();

        // The payload of the JWS is JSON content of the JWT Claims
        jws.setPayload(jwtClaims.toJson());

        // The JWT is signed using the private key
        jws.setKey(rsaJsonWebKey.getPrivateKey());

        // Set the Key ID (kid) header because it's just the polite thing to do.
        // We only have one key in this example but a using a Key ID helps
        // facilitate a smooth key rollover process
        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());

        // Set the signature algorithm on the JWT/JWS that will integrity protect the claims
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        // Sign the JWS and produce the compact serialization or the complete JWT/JWS
        // representation, which is a string consisting of three dot ('.') separated
        // base64url-encoded parts in the form Header.Payload.Signature
        // If you wanted to encrypt it, you can simply set this jwt as the payload
        // of a JsonWebEncryption object and set the cty (Content Type) header to "jwt".

        try{
            jwt = jws.getCompactSerialization();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


        // Now you can do something with the JWT. Like send it to some other party
        // over the clouds and through the interwebs.
        System.out.println("JWT: " + jwt);




        return jwt;

    }



    public static boolean verify(String jwt) throws Exception{

        JwtConsumer jwtConsumer =  getJwtConsumer() ;

        JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
        return  true;
    }

    public static String getClaim(String jwt , String key){
        JwtConsumer jwtConsumer =  getJwtConsumer() ;

        try
        {
            //  Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            String value = jwtClaims.getClaimValue(key , String.class);
            return  value;
        }
        catch (Exception e)
        {
            // InvalidJwtException will be thrown, if the JWT failed processing or validation in anyway.
            // Hopefully with meaningful explanations(s) about what went wrong.
            System.out.println("Invalid JWT! " + e);

            return  null;
        }
    }
    public static JwtConsumer getJwtConsumer(){
        // Use JwtConsumerBuilder to construct an appropriate JwtConsumer, which will
        // be used to validate and process the JWT.
        // The specific validation requirements for a JWT are context dependent, however,
        // it typically advisable to require a (reasonable) expiration time, a trusted issuer, and
        // and audience that identifies your system as the intended recipient.
        // If the JWT is encrypted too, you need only provide a decryption key or
        // decryption key resolver to the builder.
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
               // .setRequireExpirationTime() // the JWT must have an expiration time
               // .setMaxFutureValidityInMinutes(24*60) // but the  expiration time can't be too crazy
               // .setAllowedClockSkewInSeconds(300) // allow some leeway in validating time based claims to account for clock skew
               // .setRequireSubject() // the JWT must have a subject claim
               // .setExpectedIssuer("Issuer") // whom the JWT needs to have been issued by
               // .setExpectedAudience("Audience") // to whom the JWT is intended for
                .setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
                .build(); // create the JwtConsumer instance

        return jwtConsumer;
    }
    public static void main(String args[]){

        Map<String,String> claims = new HashMap<>();
        claims.put("userId","1");

        String jwt = JWTUtil.createJwt(claims);

        //InvalidJwtSignatureException
        log.debug("jwt  = " + jwt);

        int count = 0;
        String aa = "eyJraWQiOiJrMSIsImFsZyI6IlJTMjU2In0.eyJpc3MiOiJJc3N1ZXIiLCJhdWQiOiJBdWRpZW5jZSIsImV4cCI6MTU1MTQ2NTU2NSwianRpIjoiYXBZbW1PZkc3cnNkTUZrVDdQZVl6QSIsImlhdCI6MTU1MTQ2NTUwNSwibmJmIjoxNTUxNDY1Mzg1LCJzdWIiOiJzdWJqZWN0IiwidXNlcklkIjoiMSIsImdyb3VwcyI6WyJncm91cC1vbmUiLCJvdGhlci1ncm91cCIsImdyb3VwLXRocmVlIl19.SOp3PIM8oTO_niwXAe8-LpRtkcOYSjpKGO6PbYEXW6ta5KcBKRi63B-yXN3KUTODQAQlIT1ZpvKgs1ee_rU9fFhLmf-moJxr4u064OK-X4MVgY5EunvE-QhROlag7BHTooPpn5c98WvkMJ3bIJuMKbXJOWz5sCB9X7CSrHA0f8AzTiUwEld-HD4qo53VJlX3knGbmksO_Fejfh3jC7-MfYlSJ17GxRaQR6pbksf3YMq3wVAFFQKzZwFscKUFnK3SmqZ7y-6CW_Qw2d0aNKWQq9-7kDVufhEB3RipVNlJlaDQC319vQRNHSijTKS0Q7PVuzc7a6_DhK1GcZm3WbPpWA";
        String jj = "eyJraWQiOiJrMSIsImFsZyI6IlJTMjU2In0.eyJpc3MiOiJJc3N1ZXIiLCJhdWQiOiJBdWRpZW5jZSIsImV4cCI6MTU1MTQ2NDgzMCwianRpIjoiWHRXQlJRaFozQzRubXRGZ0U1QXIzUSIsImlhdCI6MTU1MTQ2NDc3MCwibmJmIjoxNTUxNDY0NjUwLCJzdWIiOiJzdWJqZWN0IiwidXNlcklkIjoiMSIsImdyb3VwcyI6WyJncm91cC1vbmUiLCJvdGhlci1ncm91cCIsImdyb3VwLXRocmVlIl19.ckQ_JzReD0PxrNrHdIgymYYpf_yhszFuiqIHodqrEdLkpaoopZLeXbPPX7PjGGEluQAn75GAeL8F3tozMH4si7rmlUPTX7PK-gIHvjevyvIcAxMnXgoZtdsoIZkEyNl5yu-XDaxF5YSkZCHBgj4iHzKRucd-CqvqH2Ott0ReYZpiviLrKhzvBpPSl0M6jrzB_18BbRBJNCTAVJEPdUGlIoxS9SP3b3zIorJvftqgZQwG0IlvztuBizkmuv2hmCu5QLj_p2ozFZ1qMq3B42k6t-ajwLcsLiQzE6jr9uGzUMXYhET9BBGN_-oM5vFr-W3jMxBv0qiWNHKH1ljel_0VoQ";
        log.debug("jwt  = " + aa);
        while (true){

            try{
                boolean result = JWTUtil.verify(aa);
                log.debug("result = {}",result);
                Thread.sleep(10000);
                log.debug("时间={}秒",++count*10);

            }
            catch(Exception ex){
                ex.printStackTrace();
                break;
            }

        }

        String email = JWTUtil.getClaim(jwt,"email");
        log.debug("email = {}",email);


    }
}
