# Hokutosai App Server
![release](https://img.shields.io/badge/release-1.3.4-green.svg)
[![server](https://img.shields.io/badge/deploy-api.hokutosai.tech-blue.svg)](https://api.hokutosai.tech)
![language](https://img.shields.io/badge/language-java8-red.svg)
![spring boot](https://img.shields.io/badge/spring%20boot-1.3.3-brightgreen.svg)
![build](https://img.shields.io/badge/build-gradle3-brightgreen.svg)

## 概要
富山高等専門学校2016年度学園祭「北斗祭 SOLE!」の公式アプリ（以下「北斗祭アプリ2016」と称す。）のアプリケーションサーバプロジェクトです。  
[Hokutosai API 2016](https://drive.google.com/open?id=0B3CA8BSIAcT1UFJQeDNDX2taMWM) が実装されています。  
北斗祭アプリについては[こちら](https://www.hokutosai.tech)をご覧ください。

## システム概略
北斗祭アプリ2016のシステム概略を示します。

<img src="https://github.com/sejour/HokutosaiAppServer/blob/master/img/Hokutosai-System.png?raw=true" width=700px>

クライアントアプリケーションはHTTPS接続で"Hokutosai API 2016"を呼び出すことにより情報の取得/操作を要求します。Hokutosai App Serverはクライアントからの要求に応じて、MySQLデータベースに格納されたリソースの取得または更新、追加、削除を行いその結果をJSONオブジェクトで返します。  
アクセスログとエラーログはMongo DBに出力されます。  
リバースプロキシとしてApache HTTP Serverを用いています。

## Hokutosai API 2016
"Hokutosai API 2016"は北斗祭アプリ2016のデータリソースへアクセスするためのRESTful WebAPIです。   
データベースに格納されている模擬店や展示、企画などのデータリソースを取得/操作するアプリケーションサーバの役割を担っています。  
各エンドポイントの詳細、レスポンスとなるJSONオブジェクトの内容、認証方法等は[リファレンス](https://drive.google.com/open?id=0B3CA8BSIAcT1UFJQeDNDX2taMWM)を参照してください。

## 開発環境
開発環境は次表の通りです。

|項目| 内容|
|:-----:|:-----:|
|言語| Java8|
|Framework| Spring Boot 1.3.3|
|ビルドツール| Gradle3|

## Dependencies
- [org.springframework.boot:spring-boot-starter-web](http://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/1.3.3.RELEASE)
- [org.springframework.boot:spring-boot-starter-data-jpa](http://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/1.3.3.RELEASE)
- [org.springframework.boot:spring-boot-starter-data-mongodb](http://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-mongodb/1.3.3.RELEASE)
- [mysql:mysql-connector-java](http://mvnrepository.com/artifact/mysql/mysql-connector-java)

## 関連リンク
- [北斗祭アプリ公式ホームページ](https://www.hokutosai.tech)
- [Hokutosai App 2016 for iOS](https://github.com/sejour/Hokutosai-iOS)
- [Hokutosai App 2016 for Android](https://github.com/SerizawaRyoji/Hokutosai-Android)
- [北斗祭 SOLE! for iOS (App Store)](https://itunes.apple.com/jp/app/bei-dou-ji-sole!/id1114411252?mt=8)
- [北斗祭 SOLE! for Android (Google Play)](https://play.google.com/store/apps/details?id=com.hokutosai.hokutosai_android&hl=ja)
- [北斗祭について](http://www.nc-toyama.ac.jp/c5/index.php/mcon/ca_life/キャンパスイベント/高専祭/kousensaih008/)
