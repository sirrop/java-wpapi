import com.studrime.wpapi.Namespace;
import com.studrime.wpapi.v2.WPAPI2;

module com.studrime.wpapi.vtwo {
    requires com.google.gson;
    requires java.net.http;
    requires com.studrime.wpapi;

    exports com.studrime.wpapi.v2;
    exports com.studrime.wpapi.v2.model;
    exports com.studrime.wpapi.v2.routing;

    provides Namespace with WPAPI2;
}