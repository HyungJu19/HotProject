
// JavaScript 함수: 배경색 변경
function toggleChoice(element) {
    // 모든 select 요소에서 'clicked' 클래스를 제거합니다.
    var selects = document.querySelectorAll('.choice');
    selects.forEach(function(select) {
        select.classList.remove('clicked');
    });
    // 클릭한 select에 'clicked' 클래스를 추가합니다.
    element.classList.toggle('clicked');


    //  클릭시 추천할목록(recommend) 변경
}