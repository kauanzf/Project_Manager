let path_selected
let id_selected

const showModalDeletar = function(path, id){
    path_selected = path
    id_selected = id
    $('#modalDeletar').modal('show')
}

const deletar = function(){
    if (path_selected != "" && id_selected != ""){
        window.location.href='/' + path_selected + '/delete/' + id_selected
    }
}