// const editBtn = document.getElementById('edit-btn');
// const deleteBtn = document.getElementById('delete-btn');
//
// editBtn.addEventListener('click', () => {
//     const editUrl = location.href + '/update';
// console.log('clicked');
//     // GET 요청 전송
//     fetch(editUrl, {
//         method: 'GET',
//     })
//         .then((response) => {
//             if (response.status === 200) {
//                 location.href = editUrl;
//             } else {
//                 alert('수정 실패: ' + response.statusText);
//             }
//         })
//         .catch((error) => {
//             console.error(error);
//             alert('오류 발생. 다시 시도해주세요.');
//         });
// });
//
// deleteBtn.addEventListener('click', () => {
//     const deleteUrl = window.location.pathname;
// console.log('clicked');
//     alert('삭제시 복구할 수 없습니다. 정말 삭제하시겠습니까?');
//
//     // DELETE 요청 전송
//     fetch(deleteUrl, {
//         method: 'DELETE',
//     })
//         .then((response) => {
//             if (response.ok) {
//                 location.href = '/board/list';
//             } else {
//                 console.log(response.status);
//                 alert('삭제 실패: ' + response.statusText);
//             }
//         })
//         .catch((error) => {
//             console.error(error);
//             alert('오류 발생. 다시 시도해주세요.');
//         });
// });


function editBoard() {
    const editUrl = location.href + '/update';
    const user = document.getElementById('user');
    const boardUser = document.getElementById('board-user');

    if(user === boardUser) {
        // GET 요청 전송
        fetch(editUrl, {
            method: 'GET',
        })
            .then((response) => {
                if (response.status === 200) {
                    location.href = editUrl;
                } else {
                    alert('수정 실패: ' + response.statusText);
                }
            })
            .catch((error) => {
                console.error(error);
                alert('오류 발생. 다시 시도해주세요.');
            });
    }else{
        alert('다른 회원의 글은 수정할 수 없습니다.');
    }


}

function deleteBoard() {
    const deleteUrl = window.location.pathname;
    // const result = window.confirm('삭제시 복구할 수 없습니다. 정말 삭제하시겠습니까?');

    if(window.confirm('삭제시 복구할 수 없습니다. 정말 삭제하시겠습니까?')){
        // DELETE 요청 전송
        fetch(deleteUrl, {
            method: 'DELETE',
        })
            .then((response) => {
                if (response.ok) {
                    location.href = '/board/list';
                } else {
                    // alert('삭제 실패: ' + response.statusText);
                    location.href = '/board/list';
                }
            })
            .catch((error) => {
                console.error(error);
                alert('오류 발생. 다시 시도해주세요.');
            });
    }

}