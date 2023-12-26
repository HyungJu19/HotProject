document.addEventListener('DOMContentLoaded', async function () {
    const openModalBtns = document.querySelectorAll('.openModalBtn');
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

    })
});
