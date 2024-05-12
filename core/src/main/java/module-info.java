import com.studrime.wpapi.Namespace;

module com.studrime.wpapi {
    requires com.google.gson;
    requires java.net.http;
    requires com.google.common;

    exports com.studrime.wpapi;
    exports com.studrime.wpapi.routing;

    uses Namespace;
}