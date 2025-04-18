# Global Market 
해외직구구매대행이 쉬워지는 쇼핑몰 통합관리 서비스.

<br>

## 🧑‍💻: Intro
네이버 스마트스토어 상품등록 기능을 구현하는 Java + React WAS (Excluding 데이터베이스 Ver.)

<br>

## 🧱: Structure
![Web App Reference Architecture V2](https://github.com/user-attachments/assets/11af0f9f-92da-4f44-b649-0c96c0ec40c7)



</br>

## 🛢️: Entity Relationship Diagram
![globalMarket](https://github.com/hyeonwoody/globlalMarket/assets/75844701/4b50710b-bc1a-45c7-b2bf-deba70fb5dd2)


</br>

## 🗓️: Development Period
2023년 12월 ~ 현재 (약 3개월), 개발 1명.

</br>

## ⚙️: Custom Settings
### 1. IP주소 업데이트 

>   **src/main/frontend/src/configuration/web/webConfig.ts** 파일, ***this.ipAddress*** 수정
>   **src/main/resources/application.properties** 파일, ***my.ipAddress*** 수정

    this.ip.Address와 my.ipAddress의 값이 동일해야 합니다.

### 2. 데이터 베이스 업데이트 

>   **src/main/resources/application.properties** 파일에서,
>   ***spring.datasource.url***,
>   ***spring.datasource.username***,
>   ***spring.datasource.password***,
>   수정.

    hikari 초기화가 되지 않아 빌드 에러 발생하는 것 방지.
    실제로 데이터 베이스를 사용하지 않도록 코드 수정을 했으니,
    임의의 테이블 생성 후 접속 가능한 데이터 베이스를 입력 하셔도 됩니다.

### 3. API 유효 인증
   
>   **src/main/resources/naverCredentials.json** 파일에
>   커머스 API의 애플리케이션ID (***client_id***), 애플리케이션 Secret (***client_secret***) 입력

    [네이버 커머스API센터 (naver.com)](https://apicenter.commerce.naver.com/ko/member/application/manage/detail;id=XXXXXXXXXXXXXXXXXXX)에서 확인 가능합니다.

</br>

## 🚀: Build & Run
### 0. 커스텀 세팅을 해주셔야 제대로 작동 됩니다.😭
### 1. 빌드
```bash
sh app.sh build // gradlew 빌드와 npm install 빌드
```
### 2. 실행
```bash
sh app.sh start // 자바 이미지 실행과 npm start
```
### 3. 종료
```bash
sh app.sh stop // 앱 프로세서 종료
```
</br>

## 📞: Contact
- 이메일: hyeonwoody@gmail.com
- 블로그: https://velog.io/@hyeonwoody
- 깃헙: https://github.com/hyeonwoody

</br>

## 🧱: Technologies Used
> Java  
> 
> React  
> 
> Github  

</br>

## 📚: Libraries Used
> Naver Commerce API  
> 
> Jackson  
> 
> Gson  
> 
> Apache Common Image  

</br>

## 🔥: Accomplishments
> [Jackson Deserializer를 활용한 API 데이터 직렬화](https://github.com/hyeonwoody/globlalMarket/wiki/Jackson-Deserializer)
> 
> 해외 E-커머스 상품 정보 크롤링
>
> Github 레포지토리 커밋을 통한 영속적인 이미지 URL 생성
> 
> 계층형 컴포넌트
