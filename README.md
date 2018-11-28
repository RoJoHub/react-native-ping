# react-native-ping

[![NPM version][npm-image]][npm-url] [![David deps][david-image]][david-url] [![node version][node-image]][node-url] [![npm download][download-image]][download-url] [![npm license][license-image]][download-url]

[npm-image]: https://img.shields.io/npm/v/react-native-ping.svg?style=flat-square
[npm-url]: https://npmjs.org/package/react-native-ping
[travis-image]: https://img.shields.io/travis/https://github.com/RoJoHub/react-native-ping.svg?style=flat-square
[travis-url]: https://travis-ci.org/https://github.com/RoJoHub/react-native-ping
[coveralls-image]: https://img.shields.io/coveralls/https://github.com/RoJoHub/react-native-ping.svg?style=flat-square
[coveralls-url]: https://coveralls.io/r/https://github.com/RoJoHub/react-native-ping?branch=master
[david-image]: https://img.shields.io/david/https://github.com/RoJoHub/react-native-ping.svg?style=flat-square
[david-url]: https://david-dm.org/https://github.com/RoJoHub/react-native-ping
[node-image]: https://img.shields.io/badge/node.js-%3E=_4.0-green.svg?style=flat-square
[node-url]: http://nodejs.org/download/
[download-image]: https://img.shields.io/npm/dm/react-native-ping.svg?style=flat-square
[download-url]: https://npmjs.org/package/react-native-ping
[license-image]: https://img.shields.io/npm/l/react-native-ping.svg

## Getting started

`$ npm install react-native-ping --save`
or
`$ yarn add react-native-ping`

### Mostly automatic installation

`$ react-native link react-native-ping`

### Manual installation

#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-ping` and add `RNReactNativePing.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNReactNativePing.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`

- Add `import com.reactlibrary.RNReactNativePingPackage;` to the imports at the top of the file
- Add `new RNReactNativePingPackage()` to the list returned by the `getPackages()` method

2. Append the following lines to `android/settings.gradle`:

```
  include ':react-native-ping'
  project(':react-native-ping').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-ping/android')
```

3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:

```
  compile project(':react-native-ping')
```

## Usage

### Get RTT (Round-trip delay time)

```javascript
import Ping from 'react-native-ping';

...

const ms = await Ping.start('114.114.114.114');
```

### Get Traffic Stats (Only iOS)

| Property             | Description               |
| -------------------- | ------------------------- |
| receivedNetworkSpeed | Download Speed per second |
| sendNetworkSpeed     | Upload Speed per second   |
| receivedNetworkTotal | Download Total            |
| sendNetworkTotal     | Upload Total              |

```javascript
import Ping from 'react-native-ping';

...

const {
  receivedNetworkSpeed,
  sendNetworkSpeed,
  receivedNetworkTotal,
  sendNetworkTotal
} = await Ping.getTrafficStats();
```
