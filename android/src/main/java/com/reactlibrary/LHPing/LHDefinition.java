package com.reactlibrary.LHPing;

public class LHDefinition {
    public enum PING_ERROR_CODE {
        Timeout("0", "PingUtil_Message_Timeout"),
        PreviousPingIsStillRuning("1", "PingUtil_Message_PreviousPingIsStillRuning"),
        HostErrorNotSetHost("2", "PingUtil_Message_HostErrorNotSetHost"),
        HostErrorUnknown("3", "PingUtil_Message_HostErrorUnknown"),
        HostErrorHostNotFound("4", "PingUtil_Message_HostErrorHostNotFound"),
        Unknown("5", "PingUtil_Message_Unknown");
        private final String message;
        private final String code;

        /**
         * @param message
         */
        PING_ERROR_CODE(final String code, final String message) {
            this.message = message;
            this.code = code;
        }

        @Override
        public String toString() {
            return message;
        }

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }
    }
}
