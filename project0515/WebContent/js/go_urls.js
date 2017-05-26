function url_home() {self.location.href = "main.jy";  return false;} //메인 페이지
function url_logout() {self.location.href = "logout.jy"; return false;} //로그아웃
function url_my_info() {self.location.href = "myinfo.jy"; return false;} //내 정보 보기
function change_me () {self.location.href = "infochange.jy"; return false;} //내 정보 변경
function url_user_info(id) {self.location.href = "userinfo.jy?id="+id; return false;} //유저 정보 보기
function url_userlist() {self.location.href = "userlist.jy"; return false;} //회원리스트 보기
function url_mylist(id) {self.location.href = "mylist.jy?id="+id; return false;} //내 글 보기
function url_mycomment(id) {self.location.href = "mycomment.jy?id="+id; return false;} //내 댓글 보기
function url_board(catid) {self.location.href = "board.jy?catid="+catid;  return false;} //자유게시판
function url_boardwrite(catid) {self.location.href = "boardwrite.jy?catid="+catid; return false;} //자유게시판 글쓰기
function change_color() {self.location.href = "colorchange.jy"; return false;} //배경색 변경