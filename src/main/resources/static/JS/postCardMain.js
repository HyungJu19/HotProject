$(document).ready(function () {

    $("body").hide();
    $("body").fadeIn(2000);

    $(function () {
        $(".btn_download").click(function (e) {
            html2canvas(document.getElementById("capture_area")).then(function (canvas) {
                let el = document.createElement("a")

                let today = new Date();
                let year = today.getFullYear()
                let month = ('0' + (today.getMonth() + 1)).slice(-2)
                let day = ('0' + today.getDate()).slice(-2)

                let dateString = year + month + day

                el.href = canvas.toDataURL("image/jpeg")
                el.download = `엽서_${dateString}.jpg` //다운로드 할 파일명 설정
                el.click()
            })
        })
    })


});


function loadFile(input) {
    let file = input.files[0]; // 선택파일 가져오기

    let newImage = document.createElement("img"); //새 이미지 태그 생성
    newImage.setAttribute("class", 'img');

    //이미지 source 가져오기
    newImage.src = URL.createObjectURL(file);
    // newImage.style.width = "100%"; //div에 꽉차게 넣으려고
    // newImage.style.height = "100%";
    newImage.style.objectFit = "scale-down"; // div에 넘치지 않고 들어가게

    //이미지를 image-show div에 추가
    let container = document.getElementById('image-show');
    container.appendChild(newImage);
    // container.style.backgroundColor = "rgb(255,255,255,0.1)"
    container.style.background = "transparent"

    document.getElementById('inputImg').style.display = "none"
}

function inputText(obj) {
    // obj.style.backgroundColor = "rgb(255,255,255,0.1)"
    obj.style.background = "transparent"
}

function changeFlowerCard1() {
    let container = document.getElementById('capture_area')
    container.style.backgroundImage = "url('/IMG/flower1.jpg')"
    document.getElementById('travel_date').style.color = "white"
    document.getElementById('region').style.color = "white"
    document.getElementById('review').style.color = "white"
}

function changeFlowerCard2() {
    let container = document.getElementById('capture_area')
    container.style.backgroundImage = "url('/IMG/flower2.jpg')"
    document.getElementById('travel_date').style.color = "white"
    document.getElementById('region').style.color = "white"
    document.getElementById('review').style.color = "white"
}

function changeWinterCard() {
    let container = document.getElementById('capture_area')
    container.style.backgroundImage = "url('/IMG/winter.jpg')"
    document.getElementById('travel_date').style.color = "black"
    document.getElementById('region').style.color = "black"
    document.getElementById('review').style.color = "black"
}

function changeCloudCard() {
    let container = document.getElementById('capture_area')
    container.style.backgroundImage = "url('/IMG/cloud.jpg')"
    document.getElementById('travel_date').style.color = "black"
    document.getElementById('region').style.color = "black"
    document.getElementById('review').style.color = "black"
}

function imgTop() {
    let parentDiv = document.getElementById('capture_area')
    let imgDiv = document.getElementById('image-show')
    let textDiv = document.getElementById('addText')
    textDiv.style.display = "block"
    imgDiv.style.height = "500px"
    parentDiv.insertBefore(imgDiv, textDiv)
}

function imgBottom() {
    let parentDiv = document.getElementById('capture_area')
    let imgDiv = document.getElementById('image-show')
    let textDiv = document.getElementById('addText')
    textDiv.style.display = "block"
    imgDiv.style.height = "500px"
    parentDiv.insertBefore(textDiv, imgDiv)
}

function imgOnly() {
    let imgDiv = document.getElementById('image-show')
    let textDiv = document.getElementById('addText')
    imgDiv.style.height = "600px"
    textDiv.style.display = "none"
}

