package com.fan.essay;

public class JwtTest {
   /* @Test
    public void testGen(){
        Map<String, Object> claim = new HashMap<>();
        claim.put("username", "zhangsan");
        claim.put("id", "1");


       String token = JWT.create()
                .withClaim("user", claim) // 添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 过期时间
                .sign(Algorithm.HMAC256("itheima")); // 指定加密算法和密钥

        System.out.println(token);
    }

    @Test
    public void testParse(){
        // 模拟前端传递的token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoiemhhbmdzYW4ifSwiZXhwIjoxNzEzMjc0NzczfQ" +
                ".ZJjltPcZ1CD_NrphKZ4_SGHguZzBSw0xvesk9oOSOCY";
        // 验证token
        JWTVerifier jwt = JWT.require(Algorithm.HMAC256("itheima")).build();
        // 解析token
        DecodedJWT decodedJWT = jwt.verify(token);

        Map<String, Claim> claims = decodedJWT.getClaims();

        System.out.println(claims.get("user"));


    }*/
}
