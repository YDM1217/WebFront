// JavaScript 파일 (예: script.js)
document.addEventListener("DOMContentLoaded", function() {
    // 각 영화 카드 요소를 선택합니다.
    var movieCards = document.querySelectorAll(".movie-card");

    // 각 카드에 이벤트 리스너를 추가합니다.
    movieCards.forEach(function(card) {
        // 카드에 마우스를 올렸을 때의 이벤트를 설정합니다.
        card.addEventListener("mouseenter", function() {
            // 마우스를 올린 카드의 영화 정보 요소를 선택합니다.
            var movieInfo = card.querySelector(".movie-info");

            // 영화 정보를 나타나게 설정합니다.
            movieInfo.style.opacity = 1;
            movieInfo.style.visibility = "visible";
        });

        // 카드에서 마우스를 뗐을 때의 이벤트를 설정합니다.
        card.addEventListener("mouseleave", function() {
            // 마우스를 뗀 카드의 영화 정보 요소를 선택합니다.
            var movieInfo = card.querySelector(".movie-info");

            // 영화 정보를 숨깁니다.
            movieInfo.style.opacity = 0;
            movieInfo.style.visibility = "hidden";
        });
    });
});
