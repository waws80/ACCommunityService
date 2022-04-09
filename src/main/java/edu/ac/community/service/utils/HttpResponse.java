package edu.ac.community.service.utils;

import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.function.Consumer;

public class HttpResponse extends ResponseEntity<Object> {


    private HttpResponse(HttpStatus status) {
        super(status);
    }

    private HttpResponse(Object body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public static HttpResponse success(Object data){
        return new CommunityBuilder(HttpStatus.OK).body(data);
    }

    public static HttpResponse paramsError(String msg){
        return new CommunityBuilder(HttpStatus.BAD_REQUEST).body(msg);
    }

    public static HttpResponse serviceError(){
        return new CommunityBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器异常，请稍后再试...");
    }

    public static HttpResponse black(){
        return new CommunityBuilder(HttpStatus.FORBIDDEN).body("当前用户已被拉黑...");
    }

    public static HttpResponse unAuth(){
        return new CommunityBuilder(HttpStatus.UNAUTHORIZED).body("非法访问...");
    }


    @SuppressWarnings("unchecked")
    private static class CommunityBuilder implements BodyBuilder {

        private final HttpStatus statusCode;

        private final HttpHeaders headers = new HttpHeaders();

        public CommunityBuilder(HttpStatus statusCode) {
            this.statusCode = statusCode;
        }

        @Override
        public BodyBuilder header(String headerName, String... headerValues) {
            for (String headerValue : headerValues) {
                this.headers.add(headerName, headerValue);
            }
            return this;
        }

        @Override
        public BodyBuilder headers(@Nullable HttpHeaders headers) {
            if (headers != null) {
                this.headers.putAll(headers);
            }
            return this;
        }

        @Override
        public BodyBuilder headers(Consumer<HttpHeaders> headersConsumer) {
            headersConsumer.accept(this.headers);
            return this;
        }

        @Override
        public BodyBuilder allow(HttpMethod... allowedMethods) {
            this.headers.setAllow(new LinkedHashSet<>(Arrays.asList(allowedMethods)));
            return this;
        }

        @Override
        public BodyBuilder contentLength(long contentLength) {
            this.headers.setContentLength(contentLength);
            return this;
        }

        @Override
        public BodyBuilder contentType(MediaType contentType) {
            this.headers.setContentType(contentType);
            return this;
        }

        @Override
        public BodyBuilder eTag(String etag) {
            if (!etag.startsWith("\"") && !etag.startsWith("W/\"")) {
                etag = "\"" + etag;
            }
            if (!etag.endsWith("\"")) {
                etag = etag + "\"";
            }
            this.headers.setETag(etag);
            return this;
        }

        @Override
        public BodyBuilder lastModified(ZonedDateTime date) {
            this.headers.setLastModified(date);
            return this;
        }

        @Override
        public BodyBuilder lastModified(Instant date) {
            this.headers.setLastModified(date);
            return this;
        }

        @Override
        public BodyBuilder lastModified(long date) {
            this.headers.setLastModified(date);
            return this;
        }

        @Override
        public BodyBuilder location(URI location) {
            this.headers.setLocation(location);
            return this;
        }

        @Override
        public BodyBuilder cacheControl(CacheControl cacheControl) {
            this.headers.setCacheControl(cacheControl);
            return this;
        }

        @Override
        public BodyBuilder varyBy(String... requestHeaders) {
            this.headers.setVary(Arrays.asList(requestHeaders));
            return this;
        }

        @Override
        public HttpResponse build() {
            return body(null);
        }

        @Override
        public HttpResponse body(@Nullable Object body) {
            return new HttpResponse(body, this.headers, this.statusCode);
        }
    }
}
