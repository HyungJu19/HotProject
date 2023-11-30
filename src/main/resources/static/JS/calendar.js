// <p id="currentDate"></p>
//
// <table id="calendarTable"></table>
// currentDate 는 현재 날짜 출력
// calendarTable 는 달력 테이블 생성
//  css 자유


// 현재 날짜 표시
var currentDateElement = document.getElementById("currentDate");
var currentDate = new Date();
currentDateElement.innerText = "현재 날짜: " + currentDate.toLocaleString();

// 2023년 11월 달력 생성
var calendarTable = document.getElementById("calendarTable");
var year = 2025;
var month = 1; // 월은 0부터 시작
var firstDay = new Date(year, month, 1);
var lastDay = new Date(year, month + 1, 0);

var headerRow = calendarTable.insertRow(0);
var daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];

for (var i = 0; i < 7; i++) {
    var cell = headerRow.insertCell(i);
    cell.innerText = daysOfWeek[i];
}

var currentDate = new Date(firstDay);
var currentRow = calendarTable.insertRow();

// 시작 요일까지 빈 셀 추가
for (var i = 0; i < firstDay.getDay(); i++) {
    var cell = currentRow.insertCell(i);
    cell.innerText = "";
}

while (currentDate.getMonth() === month) {
    if (currentDate.getDay() === 0) {
        currentRow = calendarTable.insertRow();
    }

    var cell = currentRow.insertCell(currentDate.getDay());
    cell.innerText = currentDate.getDate();

    currentDate.setDate(currentDate.getDate() + 1);
}



