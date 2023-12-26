// window.addEventListener("DOMContentLoaded", (event) => {
//     var i = 0;
//
//     function addFileInput() {
//         var filesDiv = document.getElementById("files");
//         var input = document.createElement("input");
//         input.type = "file";
//         input.name = "upfile" + i;
//         input.classList.add("form-control", "col-xs-3");
//
//         var button = document.createElement("button");
//         button.type = "button";
//         button.textContent = "삭제";
//         button.onclick = function() {
//             this.parentNode.remove();
//         };
//
//         var div = document.createElement("div");
//         div.classList.add("input-group", "mb-2");
//         div.appendChild(input);
//         div.appendChild(button);
//
//         filesDiv.appendChild(div);
//         i++;
//     }
//
//     var btnAdd = document.getElementById("btnAdd");
//     if (btnAdd) {
//         btnAdd.addEventListener("click", addFileInput);
//     } else {
//         console.error("btnAdd element not found.");
//     }
// });
$(function(){
    // TODO
    // 검증코드등...

    // [추가] 버튼 누르면 첨부파일 추가
    var i = 0;
    $("#btnAdd").click(function(){
        $("#files").append(`
            <div class="input-group mb-2">
               <input class="form-control col-xs-3" type="file" name="upfile${i}"/>
               <button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
            </div>
        `);
        i++;
    });

    // Summernot 추가
    $("#content").summernote({
        height: 300,
    });
});