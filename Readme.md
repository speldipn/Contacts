# Contacts

### 동작 시현
![](/screenshot/contacts.gif)

* Content Resolver를 사용하여 간단한 전화부 만들기

#### 권한 설정

````xml
<uses-permission android:name="android.permission.CALL_PHONE"/>
<uses-permission android:name="android.permission.READ_CONTACTS"/>
````

<br>

---
#### Loader 클래스

* Conntext로부터 Content Resolver를 가져오고, 안드로이드 자체 전화부DB에 접근하기 위해서 Uri와 Projection을 작성한다.

````java
ContentResolver resolver = ctx.getContentResolver();

Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
String[] projections = {
    ContactsContract.CommonDataKinds.Phone._ID,
    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
    ContactsContract.CommonDataKinds.Phone.NUMBER
};
````