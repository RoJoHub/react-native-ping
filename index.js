import { NativeModules } from 'react-native';

const { RNReactNativePing } = NativeModules;
class Ping {
  static async start(ipAddress) {
    const result = await RNReactNativePing.start(ipAddress);
    return result;
  }
}

export default Ping;
