type PingOptions = {
  /** Amount of time in ms to wait before reporting ping failed (Default: 1000ms) */
  timeout?: number;
  /** [iOS only] Size of the payload to send to the server (Default: 56 bytes) */
  payloadSize?: number;
};

type TrafficStats = {
  receivedNetworkSpeed: string;
  receivedNetworkTotal: string;
  sendNetworkSpeed: string;
  sendNetworkTotal: string;
};

export default class Ping {
  /**
   * Ping a specified server and return the RTT (Round-trip time) in ms
   * 
   * @param ipAddress IP Address of the server to ping (eg: 8.8.8.8)
   * @param options   Optional parameters
   */
  static start(
    ipAddress: string,
    options?: PingOptions,
  ): Promise<number>;

  static getTrafficStats(): Promise<TrafficStats>;
}
