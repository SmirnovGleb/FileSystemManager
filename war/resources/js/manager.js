$.getScript('resources/js/underscore-min.js', function() {
});
var fileStructure = null;
var currentURL = "";

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

$(document).ready(createPage(), function() {
	$('#example').DataTable();
});

function createPage() {
	var tBodyFolders = document.getElementById('foldertable');
	tBodyFolders.innerHTML = '';
	var tBodyFiles = document.getElementById('example');
	tBodyFiles.innerHTML = '';
	$
			.ajax({
				type : 'GET',
				url : "/FileSystemManager/folder",
				dataType : 'json',
				success : function(result) {
					currentURL = result.path;
					fileStructure = result;
					var foldersTemplate = _
							.template("<tr><th><%= foldername %></th><th id = '<%= folderpath %>' onClick = 'createPageWithPath(this.id)'><%= folderpath %></th><th>   </th></tr>");
					var container = document.createElement("tbody");
					container.innerHTML = drawTable(result, foldersTemplate, true);
					document.getElementById("foldertable").appendChild(
							container);
				}
			});
}

function createPageWithPath(path) {
	var tBodyFolders = document.getElementById('foldertable');
	tBodyFolders.innerHTML = '';
	var tBodyFiles = document.getElementById('example');
	tBodyFiles.innerHTML = '';
	var contractPathToFileOrFolder = path.split("/").join("<").split(".").join(
	">")
	+ "<";
	$
			.ajax({
				type : 'GET',
				url : "/FileSystemManager/folder/"+contractPathToFileOrFolder,
				dataType : 'json',
				success : function(result) {
					currentURL = result.path;
					fileStructure = result;
					var foldersTemplate = _
							.template("<tbody><tr><th><%= foldername %></th>" +
									"<th id = '<%= folderpath %>' onClick = 'createPageWithPath(this.id)'><%= folderpath %></th>" +
									"<th><button id = '<%= folderpath %>' onClick= 'deleteFileOrFolder(this.id)' class='btn btn-danger'>Remove</button></th></tr></tbody>");
					var filesTemplate = _
					.template("<tbody><tr><th><%= foldername %></th>" +
							"<th id = '<%= folderpath %>'><%= folderpath %></th>" +
							"<th><button id = '<%= folderpath %>' onClick= 'deleteFileOrFolder(this.id)' class='btn btn-danger'>Remove</button></th></tr></tbody>");
					document.getElementById("foldertable").innerHTML = drawTable(result, foldersTemplate, true);
					document.getElementById("example").innerHTML = drawTable(result, filesTemplate, false);
					var folderurl = '"'+currentURL+'"';
					document.getElementById('folderForm').innerHTML = "<input type='text' id='nameFolder'><button class='btn btn-warning' onClick = 'addfolder(" + folderurl + ")'>Add Folder</button>";
					document.getElementById('fileForm').innerHTML = "<input type='text' id='nameFile'><button class='btn btn-warning' onClick = 'addFile(" + folderurl + ")'>Add File</button>";
					document.getElementById('stepBackDiv').innerHTML = "<button class='btn btn-info' onClick = 'stepBack(" + folderurl + ")'>Step Back</button>";
				}
			});
}

function deleteFileOrFolder(filePath) {
	var pathToFolder = filePath.split("/").join("<").split(".")
			.join(">")
			;
	$.ajax({
		type : 'DELETE',
		url : "/FileSystemManager/folder/" + pathToFolder,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(msg) {
			createPageWithPath(pathForDel(filePath));
		},
		error : function(request, status, errorThrown) {
			alert("YOU ARE NOT ALLOWED");
		}
	});
}

function pathForDel(fullPath) {
	var arrayPath = fullPath.split("/");
	var curstr = "";
	for (var j = 0; j < arrayPath.length - 1; j++) {
		curstr = curstr + "/" + arrayPath[j];
	}
	return curstr.substring(1);
}

function pathArrayToString(arrayPath) {
	var curstr = "";
	for (var j = 0; j < arrayPath.length; j++) {
		curstr = curstr+ "/" + arrayPath[j];
	}
	return curstr.substring(1);
}

function addfolder(path) {
	var titleFolder = document.getElementById("nameFolder").value.split(".")
			.join(">");
	var pathToFolder = path.split(",").join("<") + "<" + titleFolder;
	$.ajax({
		type : 'PUT',
		headers : {
			'Accept' : 'application/json, text/plain, */*',
			'Content-type' : ' application/json; charset=utf-8'
		},
		url : "/FileSystemManager/folder/" + pathToFolder,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(result) {
			createPageWithPath("/" + path.split(",").join("/"));
		},
		error : function(request, status, errorThrown) {
			alert("YOU ARE NOT ALLOWED");
		}
	});
}

function stepBack(path) {
	var currentPath = path.split(",");
	currentPath.pop();
	if(currentPath.length >= 1){
		createPageWithPath(pathArrayToString(currentPath));
	}
	else {
		createPage();
	}
}

function addFile(path) {
	var titleFile = document.getElementById("nameFile").value.split(".").join(
			">");
	var pathToFolder = path.split(",").join("<") + "<" + titleFile;
	$.ajax({
		type : 'PUT',
		headers : {
			'Accept' : 'application/json, text/plain, */*',
			'Content-type' : ' application/json; charset=utf-8'
		},
		url : "/FileSystemManager/file/" + pathToFolder,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(result) {
			createPageWithPath("/" + path.split(",").join("/"));
		},
		error : function(request, status, errorThrown) {
			alert("YOU ARE NOT ALLOWED");
		}
	});
}

function getFolderPath(idfolder, result) {
	var curstr = "";
	for (var j = 0; j < result.folders[idfolder].path.length; j++) {
		curstr = curstr + "/" + result.folders[idfolder].path[j];
	}
	return curstr.substring(1);
}

function getFilePath(idfile, result) {
	var curstr = "";
	for (var j = 0; j < result.files[idfile].path.length; j++) {
		curstr = curstr + "/" + result.files[idfile].path[j];
	}
	return curstr.substring(1);
}

function drawTable(result, template, isfolder) {
	var content = '';
	var filesOrFolders = (isfolder) ? result.folders : result.files;
	_.each(filesOrFolders, function(folder, index, filesOrFolders) {
		var strPath = (isfolder) ? getFolderPath(index, result) : getFilePath(index, result);
		content += template({
			foldername : folder.name,
			folderpath : strPath,
		});
	});
	return content;
}



