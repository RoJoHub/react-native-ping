import { NativeModules, Platform } from 'react-native';

const { RNReactNativePing } = NativeModules;
class Ping {
  static async start(ipAddress) {
    const result = await RNReactNativePing.start(ipAddress);
    return result;
  }
  static async getTrafficStats() {
    if (Platform.OS !== 'ios') {
      return;
    }
    const result = await RNReactNativePing.getTrafficStats();
    return result;
  }
}

export default Ping;
