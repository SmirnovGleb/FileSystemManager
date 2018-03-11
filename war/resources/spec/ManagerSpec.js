describe(
		"Manager",
		function() {
			var testJSONString = '{"fileName":"","path":["D:"],"folders":[{"name":"$RECYCLE.BIN","path":["D:","$RECYCLE.BIN"]},{"name":"app","path":["D:","app"]},{"name":"books","path":["D:","books"]},{"name":"eclipse tests","path":["D:","eclipse tests"]},{"name":"GIT","path":["D:","GIT"]},{"name":"inst","path":["D:","inst"]},{"name":"installs","path":["D:","installs"]},{"name":"System Volume Information","path":["D:","System Volume Information"]},{"name":"Tasks","path":["D:","Tasks"]},{"name":"testFolder","path":["D:","testFolder"]}],"files":[{"name":"msdia80.dll","path":["D:","msdia80.dll"]},{"name":"pagefile.sys","path":["D:","pagefile.sys"]}]}';
			var testJSON = JSON.parse(testJSONString);
			var expectedTable = "<tbody><tr><th>msdia80.dll</th><th id = 'D:/msdia80.dll'>D:/msdia80.dll</th><th><button id = 'D:/msdia80.dll' onClick= 'deleteFileOrFolder(this.id)' class='btn btn-danger'>Remove</button></th></tr></tbody><tbody><tr><th>pagefile.sys</th><th id = 'D:/pagefile.sys'>D:/pagefile.sys</th><th><button id = 'D:/pagefile.sys' onClick= 'deleteFileOrFolder(this.id)' class='btn btn-danger'>Remove</button></th></tr></tbody>"
			var filesTemplate = _
					.template("<tbody><tr><th><%= foldername %></th>"
							+ "<th id = '<%= folderpath %>'><%= folderpath %></th>"
							+ "<th><button id = '<%= folderpath %>' onClick= 'deleteFileOrFolder(this.id)' class='btn btn-danger'>Remove</button></th></tr></tbody>");

			describe("pathForDel", function() {
				it("good data", function() {
					expect(pathForDel("D:/GIT/AntSpringMVC.zip1")).toEqual(
							"D:/GIT");
				});
				it("bad data", function() {
					expect(pathForDel("bad data")).not.toEqual("bad data");
				});
			});

			describe("pathArrayToString", function() {
				it("good data", function() {
					var testArray = [ "D:", "GIT" ];
					expect(pathArrayToString(testArray)).toEqual("D:/GIT");
				});
				it("bad data", function() {
					var testArray = [ "D:", "BAD DATA" ];
					expect(pathArrayToString(testArray)).not.toEqual("D:/GIT");
				});
			});

			describe("getFolderPath", function() {
				it("good data", function() {
					expect(getFolderPath(1, testJSON)).toEqual("D:/app");
				});
				it("bad data", function() {
					expect(getFolderPath(1, testJSON)).not.toEqual("bad data");
				});
			});

			describe("getFilePath", function() {
				it("good data",
						function() {
							expect(getFilePath(1, testJSON)).toEqual(
									"D:/pagefile.sys");
						});
				it("bad data", function() {
					expect(getFilePath(1, testJSON)).not.toEqual("bad data");
				});
			});

			describe("drawTable", function() {
				it("good data", function() {
					expect(drawTable(testJSON, filesTemplate, false)).toEqual(
							expectedTable);
				});
				it("bad data", function() {
					expect(drawTable(testJSON, filesTemplate, false)).not
							.toEqual("bad data");
				});
			});

		});
