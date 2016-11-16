## react-native-ushare-kerison
基于友盟的分享做react-native分享组件封装

【注】：由于react-native-ushare提交不了，但是npmjs又搜不到，不知道是不是有人发布了私有的库，所以安装的时候注意是react-native-ushare-kerison，引用的时候

分享支持：
  - QQ
  - QQ空间
  - 微信
  - 朋友圈
  - 新浪微博

授权支持：
  - QQ
  - 微信
  - 新浪

## 使用
1. 安装

  ```
  npm i react-native-ushare-kerison
  ```
  - Android [配置](https://github.com/GKerison/UShare)
  - iOS [配置](./ios)

2. 引入

  ```
  import UShare from 'react-native-ushare-kerison';
  ```

3. 分享
  ```
  UShare.share(shareType,title,desc,image,url,(result)=>{
    if(result === UShare.SUCCESS){
      alert('分享成功!');
    }else if(result == UShare.CANCEL){
      alert('分享取消!');
    }else if(result === UShare.FAILED){
      alert('分享失败!');
    }
  });
  ```

4. 授权
  ```
  UShare.authAndGetInfo(shareType,(result,data)=>{
    if(result === UShare.SUCCESS){
      console.log(data);
    }else if(result == UShare.CANCEL){
      alert('授权取消!');
    }else if(result === UShare.FAILED){
      alert('授权失败!');
    }
  });
  ```
