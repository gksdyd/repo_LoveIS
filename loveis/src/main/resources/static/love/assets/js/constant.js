const NAME_CHECK = RegExp(/^[가-힣]{2,6}$/);
const ID_CHECK = RegExp(/^[A-Za-z0-9_\-]{5,20}$/);
const PHONE_CHECK = RegExp(/^01[0179][0-9]{7,8}$/);
const NICKNAME_CHECK = RegExp(/^[가-힣a-zA-Z0-9]{2,10}$/);
const PASSWORD_CHECK = RegExp(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);
const EMAIL_CHECK = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);