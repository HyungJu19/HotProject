document.addEventListener('DOMContentLoaded', async function () {
    var isClickInProgress = false;
    $(document).ready(  function() {
        $('.carddd').click( function() {

            if (!isClickInProgress) {
                isClickInProgress = true;

                var contentid = $(this).data('contentid');

            }
            $.ajax({
                type: 'POST',
                url: '/search/viewCount/' + contentid,
                data: { contentid: contentid },

                success: function(response) {
                    console.log('Content ID sent successfully11111.');
                    // 서버 응답에 따른 추가 로직 구현 가능
                    updateTotalCount(contentid);
                },
                error: function(error) {
                    console.error('Error sending content ID:', error);

                },
                complete: function() {
                    isClickInProgress = false;
                }
            });
        });

        //캠핑
        $('.cardcc').click(  function() {
            try {
                console.log('AJAX 함수 호출 직전'); // AJAX 호출 전 로그


                if (!isClickInProgress) {
                    isClickInProgress = true;

                    var contentId = $(this).attr('camping-contentId');
                    console.log('contentId:', contentId); // 이 값이 정상적으로 출력되는지 확인


                    $.ajax({
                        type: 'POST',
                        url: '/search/viewCamCount/' + contentId,
                        attr: { contentId: contentId },
                        success: function(response) {
                            console.log(contentId +'뷰캠핑 카운트')
                            console.log('Content ID sent successfully222222.');
                            // 서버 응답에 따른 추가 로직 구현 가능

                            updateTotalCamCount(contentId);
                        },
                        error: function(error) {
                            console.error('Error sending content ID:', error);

                        },
                        complete: function() {
                            isClickInProgress = false;
                        }
                    });

                }
            } catch (error) {
                console.error('AJAX 호출 전 오류 발생:', error);
            }
        });
    });
    const cards = document.querySelectorAll('.carddd');
    const cardsCamping = document.querySelectorAll('.cardcc');
    const openModalBtns = document.querySelectorAll('.openModalBtn');

    // 클릭 이벤트 등록
    cards.forEach(card => {

        card.addEventListener('click', async function (event) {
            const contentId = card.getAttribute('data-contentid');
            const contentTypeId = card.getAttribute('data-contenttypeid');

            showSpinner(); // 데이터를 가져오는 중에는 로딩 표시

            try {
                await handleCardClick(contentId, contentTypeId);

                // initMap(); // 이 부분은 더이상 필요하지 않습니다.
                // addMarker(mapx, mapy); // 이 부분도 더이상 필요하지 않습니다.
            } catch (error) {
                console.error('handleCardClick 에러', error);
            } finally {
                hideSpinner(); // 데이터 가져오기 완료 후 로딩 숨기기
            }

        });
    });




    cardsCamping.forEach(card => {
        card.addEventListener('click', async function (event){
            const contentId = card.getAttribute('camping-contentId');
            const doNm = card.getAttribute('camping-doNm');

            showSpinner(); // 데이터를 가져오는 중에는 로딩 표시

            try {
                await campingCardClick(doNm,contentId);

            }catch (error){
                console.error('campingCardClick 에러', error)
            }finally {
                hideSpinner();
            }
        })
    })

    openModalBtns.forEach(btn => {
        btn.addEventListener('click', function (event) {


            fetch('/user/login') // login.html을 가져옵니다.
                .then(response => response.text()) // 텍스트로 변환합니다.
                .then(html => {
                    document.getElementById('loginModalContent').innerHTML = html; // 모달 내부에 login.html의 내용을 삽입합니다.
                    // Bootstrap Modal을 트리거하여 모달을 보이도록 합니다.
                    var myModal = new bootstrap.Modal(document.getElementById('loginModal'));
                    myModal.show();
                })
                .catch(error => console.error('Error:', error));
        })






    });

    // 투어
    $('.card-img-top').hover(
        function() {
            // 마우스가 요소 위에 있을 때
            var imgElement = $(this).siblings('img'); // 형제 이미지 요소 선택
            imgElement.addClass('animate-on-hover');
        },
        function() {
            // 마우스가 요소에서 벗어났을 때
            var imgElement = $(this).siblings('img'); // 형제 이미지 요소 선택
            imgElement.removeClass('animate-on-hover');
        }
    );


    $('.totalView').each(async function() {
        var contentId = $(this).attr('contentid');
        try {
            const totalView = await $.ajax({
                type: 'GET',
                url: '/search/totalView/' + contentId,
            });
            console.log(`contentId: ${contentId}, totalView: ${totalView}`); // 확인용 로그

            if (totalView !== undefined) {
                // 요소가 있으면 텍스트 업데이트
                $(this).text(totalView);
            } else {
                console.error('Like count element not found.');
            }
        } catch (error) {
            console.error('Error fetching like count:', error);
        }
    });

    $('.totalCamView').each(async function() {
        var contentId = $(this).attr('contentId');
        try {
            const totalView = await $.ajax({
                type: 'GET',
                url: '/search/totalCamView/' + contentId,
            });
            console.log(`contentId: ${contentId}, totalView: ${totalView}`); // 확인용 로그

            if (totalView !== undefined) {
                // 요소가 있으면 텍스트 업데이트
                $(this).text(totalView);
            } else {
                console.error('Like count element not found.');
            }
        } catch (error) {
            console.error('Error fetching like count:', error);
        }
    });


    $('.likeCount').each(async function() {
        var tourId = $(this).attr('data-restaurantid');
        try {
            const likeCount = await $.ajax({
                type: 'GET',
                url: '/api/getLikeCount/' + tourId,
            });
            console.log(`Tour ID: ${tourId}, Like Count: ${likeCount}`); // 확인용 로그

            if (likeCount !== undefined) {
                // 요소가 있으면 텍스트 업데이트
                $(this).text(likeCount);
            } else {
                console.error('Like count element not found.');
            }
        } catch (error) {
            console.error('Error fetching like count:', error);
        }
    });

    $('.likeCamCount').each(async function() {
        var campingId = $(this).attr('camping-campingid');
        try {
            const likeCount = await $.ajax({
                type: 'GET',
                url: '/api/getLikeCamCount/' + campingId,
            });
            console.log(`Tour ID: ${campingId}, Like Count: ${likeCount}`); // 확인용 로그

            if (likeCount !== undefined) {
                // 요소가 있으면 텍스트 업데이트
                $(this).text(likeCount);
            } else {
                console.error('Like count element not found.');
            }
        } catch (error) {
            console.error('Error fetching like count:', error);
        }
    });



    // 투어
    // 좋아요 버튼 초기 상태 설정
    $('.like-button').each(async function() {
        var tourId = $(this).attr('data-restaurantid');

        try {
            const response = await $.ajax({
                type: 'GET',
                url: '/api/getLikeStatus1/' + tourId,
            });
            // API 응답을 기반으로 좋아요 상태 초기화
            initializeLikeStatus(response, tourId);


        } catch (error) {
            console.error('Error fetching like status:', error);
        }
    });

    //캠핑
    $('.camlike-button').each(async function() {
        var campingId = $(this).attr('camping-campingid');

        try {
            const response = await $.ajax({
                type: 'GET',
                url: '/api/getLikeStatus2/' + campingId,
            });
            // API 응답을 기반으로 좋아요 상태 초기화
            initializeCamLikeStatus(response, campingId);


        } catch (error) {
            console.error('Error fetching like status:', error);
        }
    });

    // 투어
    function initializeLikeStatus(result, tourId) {
        var button = $('[data-restaurantid="' + tourId + '"]');
        console.log("리절트" + result);
        console.log("button" + button);

        if (result === 1) {
            // 이미 블럭된 경우
            updateButtonState(button, false);
        } else {
            // 좋아요 가능한 경우
            updateButtonState(button, true);
        }
    }

    //캠핑
    function initializeCamLikeStatus(result, campingId) {
        var button = $('[camping-campingid="' + campingId + '"]');
        console.log("리절트" + result);
        console.log("button" + button);

        if (result === 1) {
            // 이미 블럭된 경우
            updateButtonCamState(button, false);
        } else {
            // 좋아요 가능한 경우
            updateButtonCamState(button, true);
        }
    }



    function updateButtonState(button, liked) {
        if (liked) {
            // 좋아요가 블럭으로 변경될 때
            button.siblings('.like-button').show();
        } else {
            // 블럭이 좋아요로 변경될 때
            button.show();
            button.siblings('.like-button').hide();
        }
    }

    function updateButtonCamState(button, liked) {
        if (liked) {
            // 좋아요가 블럭으로 변경될 때
            button.siblings('.camlike-button').show();
        } else {
            // 블럭이 좋아요로 변경될 때
            button.show();
            button.siblings('.camlike-button').hide();
        }
    }
    $('.like-btn').click(function() {
        var tourId = $(this).attr('data-restaurantid'); // 수정된 부분
        likeTour(tourId);
        $(this).find('img').addClass('animate-heart');
        console.log(tourId+'좋아요 버튼 투어')
        setTimeout(() => {
            $(this).hide();
            $(this).siblings('.unlike-btn').show();
        }, 200); // 0.2초 후에 실행 (애니메이션 시간과 일치)

    });

    $('.unlike-btn').click(function() {
        var tourId = $(this).attr('data-restaurantid'); // 수정된 부분
        unlikeTour(tourId);
        $(this).find('img').addClass('animate-heart');
        console.log(tourId+ '좋아요 취소버튼 투어')
        $(this).hide();
        $(this).siblings('.like-btn').show().addClass('animated');
    });


    //캠핑

    $('.camlike-btn').click(function() {
        var campingId = $(this).attr('camping-campingid'); // 수정된 부분
        likeCamping(campingId);
        $(this).find('img').addClass('animate-heart');
        console.log(campingId+'캠핑 좋아요버튼')
        setTimeout(() => {
            $(this).hide();
            $(this).siblings('.camunlike-btn').show();
        }, 200); // 0.2초 후에 실행 (애니메이션 시간과 일치)

    });

    $('.camunlike-btn').click(function() {
        var campingId = $(this).attr('camping-campingid'); // 수정된 부분
        unlikeCamping(campingId);
        $(this).find('img').addClass('animate-heart');
        console.log(campingId + '캠핑 싫어요')
        $(this).hide();
        $(this).siblings('.camlike-btn').show().addClass('animated');
    });
});


//투어
function likeTour(tourId) {
    console.log(tourId)
    $.ajax({
        type: 'POST',
        url: '/search/likeOk/' + tourId,
        success: function(response) {
            if (response === 'ok') {
                console.log('좋아요가 반영되었습니다.');
                updateLikeCount(tourId); // 좋아요 수 업데이트
            } else if (response === 'NotAuthenticated') {
                console.log('인증되지 않았습니다.');
            }
        },
        error: function(error) {
            console.error('요청 실패: ', error);
        }
    });
}

function unlikeTour(tourId) {
    console.log(tourId)
    $.ajax({
        type: 'POST',
        url: '/search/likeX/' + tourId,

        success: function(response) {
            if (response === 'x') {
                console.log('좋아요가 취소되었습니다.');
                updateLikeCount(tourId); // 좋아요 수 업데이트
            } else if (response === 'NotAuthenticated') {
                console.log('인증되지 않았습니다.');
            }
        },
        error: function(error) {
            console.error('요청 실패: ', error);
        }

    });
}

//캠핑
function likeCamping(campingId) {
    console.log(campingId)
    $.ajax({
        type: 'POST',
        url: '/search/likeOk1/' + campingId,
        success: function(response) {
            if (response === 'ok') {
                console.log('좋아요가 반영되었습니다.');
                updateCamLikeCount(campingId); // 좋아요 수 업데이트
            } else if (response === 'NotAuthenticated') {
                console.log('인증되지 않았습니다.');
            }
        },
        error: function(error) {
            console.error('요청 실패: ', error);
        }
    });
}

function unlikeCamping(campingId) {
    console.log(campingId)
    $.ajax({
        type: 'POST',
        url: '/search/likeX1/' + campingId,

        success: function(response) {
            if (response === 'x') {
                console.log('좋아요가 취소되었습니다.');
                updateCamLikeCount(campingId); // 좋아요 수 업데이트
            } else if (response === 'NotAuthenticated') {
                console.log('인증되지 않았습니다.');
            }
        },
        error: function(error) {
            console.error('요청 실패: ', error);
        }

    });
}
// tourId에 원하는 투어 ID를 넣어 호출




async function updateTotalCount(contentId) {
    try {
        console.log('Before AJAX request');
        const totalView = await $.ajax({
            type: 'GET',
            url: '/search/totalView/' + contentId,

        });

        console.log(`contentId: ${contentId}, totalView: ${totalView}`);
        console.log(totalView)
        if (totalView !== undefined) {
            $('.totalView[contentid="' + contentId + '"]').text(totalView);
            console.log(totalView + ' 업데이트 투어뷰카운트')
        } else {
            console.error('Total view count element not found.');
        }
    } catch (error) {
        console.error('Error fetching total view count:', error);
    }
    console.log('After AJAX request');
}

async function updateTotalCamCount(contentId) {
    try {
        console.log('Before AJAX request');
        const totalView = await $.ajax({
            type: 'GET',
            url: '/search/totalCamView/' + contentId,

        });

        console.log(`contentId: ${contentId}, totalView: ${totalView}`);
        console.log(totalView + '캠핑 뷰카운트 ')
        console.log(contentId +  '캠핑 컨텐츠아이디 ')
        if (totalView !== undefined) {
            $('.totalCamView[contentId="' + contentId + '"]').text(totalView);
            console.log(totalView)
            console.log(contentId +  '캠핑 컨텐츠아이디 1')
        } else {
            console.error('Total view count element not found.');
        }
    } catch (error) {
        console.error('Error fetching total view count:', error);
    }
    console.log('After AJAX request');
}

async function updateLikeCount(tourId) {
    try {
        const likeCount = await $.ajax({
            type: 'GET',
            url: '/api/getLikeCount/' + tourId,
        });

        console.log(`Tour ID: ${tourId}, Like Count: ${likeCount}`); // 확인용 로그

        if (likeCount !== undefined) {
            // 좋아요 수 업데이트
            $('.likeCount[data-restaurantid="' + tourId + '"]').text(likeCount);
        } else {
            console.error('Like count element not found.');
        }
    } catch (error) {
        console.error('Error fetching like count:', error);
    }
}

async function updateCamLikeCount(campingId) {
    console.log(campingId + '11111')
    try {
        const likeCount = await $.ajax({
            type: 'GET',
            url: '/api/getLikeCamCount/' + campingId,
        });

        console.log(`Tour ID: ${campingId}, Like Count: ${likeCount}`); // 확인용 로그

        if (likeCount !== undefined) {
            // 좋아요 수 업데이트
            $('.likeCamCount[camping-campingid="' + campingId + '"]').text(likeCount);
        } else {
            console.error('Like count element not found.');
        }
    } catch (error) {
        console.error('Error fetching like count:', error);
    }
}