// 오늘 날짜로부터 만 19세를 계산
var date=new Date(); //오늘 날짜
var year = date.getFullYear(); //년도
var month = ("0" + (1 + date.getMonth())).slice(-2);//월
var day = ("0" + date.getDate()).slice(-2);//일
var today=year+'-'+month+'-'+day; //오늘 날짜
var check = (year-19)+'-'+month+'-'+day; //만 19세

console.log(today);
console.log(check);

document.getElementById('html5-date-input').value=today;
document.getElementById('html5-date-input').max=check;

document.getElementById('updateBirth').max=check;