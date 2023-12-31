
// 스크롤 이벤트

document.addEventListener('DOMContentLoaded', function () {
    const slideContainer = document.querySelector('.slide-container');  // 슬라이드 할 페이지 전체를 감싼 컨테이너 정의
    const slides = document.querySelectorAll('.slide');     //  슬라이드 될 페이지 정의
    let currentIndex = 0; // 현재 보여지는 슬라이드의 인덱스를 나타내는 변수를 초기화

    function scrollToSlide(index) {  //  슬라이드를 특정 인덱스로 스크롤하는 함수를 정의
        const slideHeight = slides[index].clientHeight; // 슬라이드의 높이를 가져옴
        const newPosition = index * slideHeight;
        window.scrollTo({
            top: newPosition,
            behavior: 'smooth'
        });
    }

    slideContainer.addEventListener('wheel', function (event) {
        // slideContainer 내에서 스크롤 할시 이벤트 추가
        event.preventDefault();  //  기본 스크롤 동작을 막습니다.

        if (event.deltaY > 0) {
            // 아래로 스크롤
            currentIndex = Math.min(currentIndex + 1, slides.length - 1);
        } else {
            // 위로 스크롤
            currentIndex = Math.max(currentIndex - 1, 0);

        }
        scrollToSlide(currentIndex);
    });




// main-content 클릭 이벤트

    $(document).ready(function() {
        $('.main-container').on('click', '.content-box', function () {
            // 기존에 .content가 있으면 제거하고 해당 내용을 content-box로 이동
            const existingContent = $('.content');
            if (existingContent) {
                const contentText = existingContent.text();
                existingContent.removeClass('content').addClass('content-box').text(contentText);
            }

            // content-box를 복제하여 다시 삽입
            const clonedBox = $(this).clone(true);
            clonedBox.removeClass('content-box').addClass('content'); // 클래스 수정
            $('.main-container').prepend(clonedBox);

            // 클릭한 content-box 제거
            $(this).remove();
        });
    });










// 스케일 조정 이벤트.
    // let box = "box";
    // let content = "content";
    // const scaleEvent = document.querySelectorAll('.content-box');
    // [].forEach.call(scaleEvent, function (scale) {
    //     scale.addEventListener('click', function () {
    //         console.log(scaleEvent.length)
    //         for (let i = 1; i < scaleEvent.length + 1; i++) {
    //             if (this.classList.contains(box + i)) {
    //                 this.classList.toggle(content + i);
    //             }
    //         }
    //     })
    // })






});


