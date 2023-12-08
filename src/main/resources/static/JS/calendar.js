window.onload = function() {


// 현재 날짜 표시
let currentDateElement = document.getElementById("currentDate");
let currentDate1 = new Date();
currentDateElement.innerText = "현재 날짜: " + currentDate1.toLocaleString();

// 2023년 11월 달력 생성
let calendarTable = document.getElementById("calendarTable");
let year = 2025;
let month = 1; // 월은 0부터 시작
let firstDay = new Date(year, month, 1);
let lastDay = new Date(year, month + 1, 0);

let headerRow = calendarTable.insertRow(0);
let daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];

for (let i = 0; i < 7; i++) {
    let cell = headerRow.insertCell(i);
    cell.innerText = daysOfWeek[i];
}

let currentDate2 = new Date(firstDay);
let currentRow = calendarTable.insertRow();

// 시작 요일까지 빈 셀 추가
for (let i = 0; i < firstDay.getDay(); i++) {
    let cell = currentRow.insertCell(i);
    cell.innerText = "";
}

while (currentDate2.getMonth() === month) {
    if (currentDate2.getDay() === 0) {
        currentRow = calendarTable.insertRow();
    }

    let cell = currentRow.insertCell(currentDate2.getDay());
    cell.innerText = currentDate2.getDate();

    currentDate2.setDate(currentDate2.getDate() + 1);
}


}




