# react-native-ping

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

```javascript
import RNReactNativePing from 'react-native-ping';

// TODO: What to do with the module?
RNReactNativePing;
```
