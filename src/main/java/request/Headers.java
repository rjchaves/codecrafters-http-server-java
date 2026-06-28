package request;

public record Headers(
        String host,
        String userAgent,
        String accept
) {
}
