public String generateToken(Long userId, String email, Set<String> roles) {
    String rolesCsv = String.join(",", roles);

    return Jwts.builder()
            .claim("userId", userId)
            .claim("email", email)
            .claim("roles", rolesCsv)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
}
