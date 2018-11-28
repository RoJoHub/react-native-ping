import { NativeModules } from 'react-native';

const { RNReactNativePing } = NativeModules;
class Ping {
  static async start(ipAddress) {
    const result = await RNReactNativePing.start(ipAddress);
    return result;
  }
  static async getTrafficStats() {
    const result = await RNReactNativePing.getTrafficStats();
    return result;
  }
}

export default Ping;
