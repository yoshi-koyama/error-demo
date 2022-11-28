# このプロジェクトについて

例外をthrowしなおすときにcauseを引数にいれるかいれないかでログがどう変わるかを確認する。  

# 前提

- Java 17
- Spring Boot

# 起動

お使いのエディターで起動してください。 

# このプロジェクトについて

2つのエンドポイントがあります。  
`/hello`  
`/hello2`  

`/hello`ではControllerでthrowし直すときにServiceでthrowされたRuntimeExceptionのコンストラクタにわたしています。  
https://github.com/yoshi-koyama/error-demo/blob/521ed6d811e7ebeea042611a046e27dec5bda430/src/main/java/com/example/errordemo/HelloController.java#L23-L25  
一方、`/hello2`ではControllerでthrowし直すときにServiceでthrowされたRuntimeExceptionをコンストラクタにわたしていません。  
https://github.com/yoshi-koyama/error-demo/blob/521ed6d811e7ebeea042611a046e27dec5bda430/src/main/java/com/example/errordemo/HelloController.java#L33-L35  

各RuntimeExceptionにわたしている"something bad happened in hello() of HelloService"などの例外メッセージについて、  
`/hello`にリクエストした場合には、HelloServiceの例外に設定されたメッセージも出力されますが、  
`/hello2`にリクエストした場合には、HelloServiceの例外に設定されたメッセージは出力されないことを確認します。  


それから、ログの出力箇所はこちらです。  

https://github.com/yoshi-koyama/error-demo/blob/521ed6d811e7ebeea042611a046e27dec5bda430/src/main/java/com/example/errordemo/CustomExceptionHandler.java#L21

# /helloにリクエスト

下記エンドポイントにリクエストする。  

```bash
% curl http://localhost:8080/hello 
{"message":"something bad happened"}%
```

エディターに出力されるログを確認してください。  

`something bad happened in hello() of HelloService`が出力されているはずです。  
```
2022-11-28T22:43:28.288+09:00 ERROR 56842 --- [nio-8080-exec-8] c.e.errordemo.CustomExceptionHandler     : an exception was thrown

java.lang.RuntimeException: something bad happened in hello() of HelloController
	at com.example.errordemo.HelloController.hello(HelloController.java:24) ~[main/:na]
（略）
	at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]
Caused by: java.lang.RuntimeException: something bad happened in hello() of HelloService
	at com.example.errordemo.HelloService.hello(HelloService.java:10) ~[main/:na]
	at com.example.errordemo.HelloController.hello(HelloController.java:22) ~[main/:na]
	... 50 common frames omitted
```

# /hello2にリクエスト

下記エンドポイントにリクエストする。

```bash
% curl http://localhost:8080/hello2
{"message":"something bad happened"}%
```

エディターに出力されるログを確認してください。  
`something bad happened in hello2() of HelloService`が出力されていないはずです。  

```
2022-11-28T22:44:24.342+09:00 ERROR 56842 --- [nio-8080-exec-9] c.e.errordemo.CustomExceptionHandler     : an exception was thrown

java.lang.RuntimeException: something bad happened in hello2() of HelloController
	at com.example.errordemo.HelloController.hello2(HelloController.java:34) ~[main/:na]
（略）
	at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]
```

