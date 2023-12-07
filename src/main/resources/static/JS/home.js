document.addEventListener('DOMContentLoaded', function () {
    const slideContainer = document.querySelector('.slide-container');
    // 슬라이드 할 페이지 전체를 감싼 컨테이너 정의
    const slides = document.querySelectorAll('.slide');
    let currentIndex = 0;

    function scrollToSlide(index) {
        const newPosition = index * window.innerHeight;
        window.scrollTo({
            top: newPosition,
            behavior: 'smooth'
        });
    }

    slideContainer.addEventListener('wheel', function (event) {
        // slideContainer 내에서 스크롤 할시 이벤트 추가
        event.preventDefault();

        if (event.deltaY > 0) {
            // 아래로 스크롤
            currentIndex = Math.min(currentIndex + 1, slides.length - 1);
        } else {
            // 위로 스크롤
            currentIndex = Math.max(currentIndex - 1, 0);

        }
        scrollToSlide(currentIndex);
    });

    // 초기 설정
    // updateSlideHeight();

function left() {

}

function right(){

}

});


