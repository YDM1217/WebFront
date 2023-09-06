// 전체 스크립트 엄격 모드 구문
// https://www.w3schools.com/js/js_strict.asp
'use strict';
// Make navbar transparent when it is on the top
//querySelector 이용 시, ctrl or cmd 를 이용해서 함수가 정의된 곳으로 이동 가능.
const navbar = document.querySelector('#navbar');
const navbarHeight = navbar.getBoundingClientRect().height;
// 스크롤이 될 때마다 {} 블럭 안을 실행
document.addEventListener('scroll', () => {
//console.log(window.scrollY);
//console.log(`navbarHeight: ${navbarHeight}`);
if(window.scrollY > navbarHeight) {
navbar.classList.add('navbar__dark');
}else{
navbar.classList.remove('navbar__dark');
}
});
//Handle scrolling when tapping on the navbar menu
const navbarMenu = document.querySelector('.navbar__menu');
navbarMenu.addEventListener('click', (event) => {
const target = event.target;
const link = target.dataset.link;
if (link == null) {
return;
}
//console.log(event.target.dataset.link);
navbarMenu.classList.remove('open');
scrollIntoView(link);
});
//Navbar toggle button for small screen
const navbarToggleBtn = document.querySelector('.navbar__toggle-btn');
navbarToggleBtn.addEventListener('click', () => {
My Devlog - 나의 공부 기록My Devlog - 나의… 구독하기
navbarMenu.classList.toggle('open');
});
// Handle click on "Resume" on navbar
const navbarMenuResume = document.getElementById('downloadLink');
navbarMenuResume.addEventListener('click', function(event){
download();
});
function download(){
if(confirm('Download my resume?')){
window.open(
"https://drive.google.com/file/d/1FL80fgUt3NVjzuJkkTYkxJ1o_t5YtsUO/view?usp=sharin
g",
"_blank"
);
}
return false;
}
//Handle click on "contact me" button on home
const homeContactBtn = document.querySelector('.home__contact');
homeContactBtn.addEventListener('click', () => {
scrollIntoView('#contact');
});
//Make home slowly fade to transparent as the window scrolls down
const home = document.querySelector('.home__container');
const homeHeight = home.getBoundingClientRect().height;
document.addEventListener('scroll', () => {
//console.log(1 - window.scrollY / homeHeight);
home.style.opacity = 1 - window.scrollY / homeHeight;
})
//Show "arrow up" button when scrolling down
const arrowUp = document.querySelector('.arrow-up');
document.addEventListener('scroll', () => {
if(window.scrollY > homeHeight / 2) {
arrowUp.classList.add('visible');
}else {
arrowUp.classList.remove('visible');
}
});
//Handle click on "arrow up" button
arrowUp.addEventListener('click', () => {
scrollIntoView('#home');
});
//Work: Projects
const workBtnContainer = document.querySelector('.work__categories');
const projectContainer = document.querySelector('.work__projects');
const projects = document.querySelectorAll('.project');
workBtnContainer.addEventListener('click', (e) => {
const filter = e.target.dataset.filter || e.target.parentNode.dataset.filter;
if (filter == null) {
return;
}
// console.log(filter);
//Remove selection from the previous item and select the new one
const active = document.querySelector('.category__btn.selected');
if (active != null){
active.classList.remove('selected');
} //if 조건 안주면 에러남 -- main.js:80 Uncaught TypeError: Cannot read property 'classLis
t' of null at HTMLDivElement.<anonymous>
const target =
e.target.nodeName === 'BUTTON' ? e.target : e.target.parentNode;
//nodeName이 button이 아니면 부모노드(span의 경우 부모노드가 button)를 target으로 지정
e.target.classList.add('selected');
projectContainer.classList.add('anim-out');
setTimeout(() => {
projects.forEach((project) => {
console.log(project);
if (filter === '*' || filter === project.dataset.type) {
project.classList.remove('invisible');
} else {
project.classList.add('invisible');
}
});
projectContainer.classList.remove('anim-out');
}, 300); // 0.3초가 지나면 우리가 등록한 animation을 다시 없앰
// opacity 0 -> 1 (원상복귀)
// console.log(`--------------`);
// for(let project of projects) {
// console.log(project);
// }
// console.log(`--------------`);
// let project;
// for (let i = 0; i < projects.length; i++) {
// project = projects[i];
// console.log(project);
// }
});
function scrollIntoView(selector){
const scrollTo = document.querySelector(selector);
scrollTo.scrollIntoView({behavior: 'smooth'});}
