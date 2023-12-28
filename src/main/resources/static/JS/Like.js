async function fetchData(contentId, contentTypeId) {
    const TIMEOUT_DURATION = 900; // 타임아웃 기간 (0.3초)
    try {
        const controller = new AbortController();
        const timeoutId = setTimeout(() => controller.abort(), TIMEOUT_DURATION); // 타임아웃 설정

        const response = await fetch(`/searchDetail/${contentId}/${contentTypeId}`, {
            signal: controller.signal // AbortController의 시그널 사용
        });

        clearTimeout(timeoutId); // 타임아웃 해제
        const data = await response.json();

        return data;
    } catch (error) {
        console.error('데이터를 가져오는 중 에러 발생:', error);
        // 타임아웃이 발생한 경우에만 재시도하도록 추가 처리
        if (error.name === 'AbortError') {
            console.log('재시도 중...');
            // 재시도 로직 추가 (예: 다시 fetchData 호출)
            return fetchData(contentId, contentTypeId);
        }
        throw error;
    }
}


async function handleCardClick(contentId, contentTypeId) {

    loadGoogleMaps();
    try {

        let data;
        const cachedData = cachedDataArray.find(item => item.tour.contentid === contentId);

        if (cachedData) {
            // 저장된 데이터 활용
            data = cachedData;
        } else {
            // 저장된 데이터가 없을 경우, 데이터를 가져오도록 요청
            data = await fetchData(contentId, contentTypeId);
            cachedDataArray.push(data);
        }


        // 이후에는 data를 활용하여 필요한 작업을 수행할 수 있습니다.
        console.log(data);

        mapx = parseFloat(data.tour.mapx);
        mapy = parseFloat(data.tour.mapy);

        if (data.tourDetail && data.tour) {
            const modalTitle = document.getElementById('modalTitle');
            const modalBody = document.getElementById('modalBody');
            modalTitle.textContent = data.tour.title ? data.tour.title : '사용할 수 없음';
            const myModal = new bootstrap.Modal(document.getElementById('tourModal'));

            modalBody.innerHTML = `
                            <!-- modalBody의 내용 작성 -->
            <div class="tour-container">
                <div class="tour-top">
                    <div class="tour-top-img">
                    <p>No.${data.tour.contentid}</p>
                    <img src="${data.tour.firstimage ? data.tour.firstimage : 'Image not available'}" alt="Tour Image" style="width: 400px; border-radius: 10px; height: auto; margin-bottom: 10px;">
                    </div>
                    <div class="tour-top-info">
                    <h2>${data.tour.title ? data.tour.title : '없음'}</h2>
                      ${data.tour.zipcode ? `<p>우편번호: ${data.tour.zipcode}</p>` : ''}
                      ${data.tour.addr1 ? `<p>주 소: ${data.tour.addr1}</p>` : ''}








<!--                    음식점-->
                    ${data.tourDetail.response.body.items.item[0].chkcreditcardfood  ? `<p>신용카드가능정보: ${data.tourDetail.response.body.items.item[0].chkcreditcardfood }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].discountinfofood  ? `<p>할인정보: ${data.tourDetail.response.body.items.item[0].discountinfofood }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].firstmenu  ? `<p>대표메뉴: ${data.tourDetail.response.body.items.item[0].firstmenu }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].infocenterfood  ? `<p>문의및안내: ${data.tourDetail.response.body.items.item[0].infocenterfood }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].kidsfacility  ? `<p>어린이놀이방여부: ${data.tourDetail.response.body.items.item[0].kidsfacility }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].opendatefood  ? `<p>개업일: ${data.tourDetail.response.body.items.item[0].opendatefood }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].opentimefood  ? `<p>영업시간: ${data.tourDetail.response.body.items.item[0].opentimefood }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].packing  ? `<p>포장가능: ${data.tourDetail.response.body.items.item[0].packing }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].parkingfood  ? `<p>주차시설: ${data.tourDetail.response.body.items.item[0].parkingfood }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].reservationfood  ? `<p>예약안내: ${data.tourDetail.response.body.items.item[0].reservationfood }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].restdatefood  ? `<p>쉬는날: ${data.tourDetail.response.body.items.item[0].restdatefood }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].scalefood  ? `<p>쉬는날: ${data.tourDetail.response.body.items.item[0].scalefood }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].seat  ? `<p>좌석수: ${data.tourDetail.response.body.items.item[0].seat }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].smoking  ? `<p>금연/흡연여부: ${data.tourDetail.response.body.items.item[0].smoking }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].treatmenu  ? `<p>취급메뉴: ${data.tourDetail.response.body.items.item[0].treatmenu }</p>` : ''}
                    ${data.tourDetail.response.body.items.item[0].lcnsno  ? `<p>인허가번호: ${data.tourDetail.response.body.items.item[0].lcnsno }</p>` : ''}
                    </div>
                    </div>
                    </div>





                    `;
            myModal.show();


            // addMarker 함수를 호출하여 마커 추가

            initMap();
            addMarker(mapx, mapy);


        }


    } catch (error) {
        console.error('Error handling card click:', error);
        throw error;
    }
}


document.addEventListener('DOMContentLoaded', async function () {
    var isClickInProgress = false;
    $(document).ready(function () {
        $('.carddd').click(function () {

            if (!isClickInProgress) {
                isClickInProgress = true;

                var contentid = $(this).data('contentid');

            }
            $.ajax({
                type: 'POST',
                url: '/search/viewCount/' + contentid,
                data: {contentid: contentid},

                success: function (response) {
                    console.log('Content ID sent successfully11111.');
                    // 서버 응답에 따른 추가 로직 구현 가능
                    updateTotalCount(contentid);
                },
                error: function (error) {
                    console.error('Error sending content ID:', error);

                },
                complete: function () {
                    isClickInProgress = false;
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


                try {
                    await handleCardClick(contentId, contentTypeId);

                    // initMap(); // 이 부분은 더이상 필요하지 않습니다.
                    // addMarker(mapx, mapy); // 이 부분도 더이상 필요하지 않습니다.
                } catch (error) {
                    console.error('handleCardClick 에러', error);
                } finally {
                }

            });
        });

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
            function () {
                // 마우스가 요소 위에 있을 때
                var imgElement = $(this).siblings('img'); // 형제 이미지 요소 선택
                imgElement.addClass('animate-on-hover');
            },
            function () {
                // 마우스가 요소에서 벗어났을 때
                var imgElement = $(this).siblings('img'); // 형제 이미지 요소 선택
                imgElement.removeClass('animate-on-hover');
            }
        );
        $('.totalView').each(async function () {
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

        $('.likeCount').each(async function () {
            var tourId = $(this).attr('data-restaurantid');
            console.log(tourId + "이거나오나!!!")
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
        });    // 투어
        // 좋아요 버튼 초기 상태 설정
        $('.like-button').each(async function () {
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
        });   // 투어
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

        $('.like-btn').click(function () {
            var tourId = $(this).attr('data-restaurantid'); // 수정된 부분
            likeTour(tourId);
            $(this).find('img').addClass('animate-heart');
            console.log(tourId + '좋아요 버튼 투어')
            setTimeout(() => {
                $(this).hide();
                $(this).siblings('.unlike-btn').show();
            }, 200); // 0.2초 후에 실행 (애니메이션 시간과 일치)

        });

        $('.unlike-btn').click(function () {
            var tourId = $(this).attr('data-restaurantid'); // 수정된 부분
            unlikeTour(tourId);
            $(this).find('img').addClass('animate-heart');
            console.log(tourId + '좋아요 취소버튼 투어')
            $(this).hide();
            $(this).siblings('.like-btn').show().addClass('animated');
        });
    });
});

        //투어
        function likeTour(tourId) {
            console.log(tourId)
            $.ajax({
                type: 'POST',
                url: '/search/likeOk/' + tourId,
                success: function (response) {
                    if (response === 'ok') {
                        console.log('좋아요가 반영되었습니다.');
                        updateLikeCount(tourId); // 좋아요 수 업데이트
                    } else if (response === 'NotAuthenticated') {
                        console.log('인증되지 않았습니다.');
                    }
                },
                error: function (error) {
                    console.error('요청 실패: ', error);
                }
            });
        }

        function unlikeTour(tourId) {
            console.log(tourId)
            $.ajax({
                type: 'POST',
                url: '/search/likeX/' + tourId,

                success: function (response) {
                    if (response === 'x') {
                        console.log('좋아요가 취소되었습니다.');
                        updateLikeCount(tourId); // 좋아요 수 업데이트
                    } else if (response === 'NotAuthenticated') {
                        console.log('인증되지 않았습니다.');
                    }
                },
                error: function (error) {
                    console.error('요청 실패: ', error);
                }

            });
        }

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

    function addMarker(mapx, mapy) {
        const mapElementInModal = document.getElementById('mapInModal');

        const longitude = mapx || 0; // 초기값 설정
        const latitude = mapy || 0; // 초기값 설정

        if (!map) {
            map = new google.maps.Map(mapElementInModal, {
                center: {lat: latitude, lng: longitude},
                zoom: 12,
            });
        }

        if (map) {
            const marker = new google.maps.Marker({
                position: {lat: latitude, lng: longitude},
                map: map
            });
        } else {
            console.error('Google Maps is not initialized.');
        }

        console.log(latitude);
        console.log(longitude);
    }




