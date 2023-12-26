$(document).ready(function () {
    $('.my-list').hide();
    $('.side-bar-btn').on('click', function () {
        let click = this.classList;
        console.log(click[1]);

        switch (click[1]) {
            case "like-view" :
                remove();
                $(this).addClass("clicked")
                $('#like-view').show();
                break;
            case "my-post" :
                remove();
                $(this).addClass("clicked")
                $('#my-post').show();
                break;
        }

        function remove() {
            $('.side-bar-btn').removeClass("clicked")
            $('.my-list').hide();
        }
    })

})



