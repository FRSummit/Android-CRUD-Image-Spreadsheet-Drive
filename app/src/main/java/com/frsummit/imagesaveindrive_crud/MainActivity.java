package com.frsummit.imagesaveindrive_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button addUser,viewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addUser=(Button)findViewById(R.id.btn_add_user);
        viewUser=(Button)findViewById(R.id.btn_list_user);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),AddUser.class);
                startActivity(intent);

            }
        });

        viewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserList.class);
                startActivity(intent);

            }
        });
    }
}


    /**
     * App Script
     *
     *

 function doGet(e) {
 var op = e.parameter.action;

 if(op == "insert")
 return insert_value(e);

 if(op == "readAll")
 return read_all_value(e);
 }

 function doPost(e) {
 var op = e.parameter.action;

 if(op == "insert")
 return insert_value(e);

 if(op == "readAll")
 return read_all_value(e);
 }

 var SCRIPT_PROP = PropertiesService.getScriptProperties();
 // see: https://developers.google.com/apps-script/reference/properties/

function setup() {
    var doc = SpreadsheetApp.getActiveSpreadsheet();
    SCRIPT_PROP.setProperty("key", doc.getId());
}

    function insert_value(e) {
//  var doc     = SpreadsheetApp.openById(SCRIPT_PROP.getProperty("key"));
        var doc     = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/198He_hovDJP75xfzJS3qtL0S1z5ox5K0xzZius4oBsg/edit#gid=0");
        var sheet   = doc.getSheetByName('Sheet1'); // select the responses sheet

        var uId    = e.parameter.uId;
        var  uName = e.parameter.uName;
        var uImage = e.parameter.uImage;

        var dropbox = "ANDROID USERS IMAGE";
        var folder, folders = DriveApp.getFoldersByName(dropbox);

        if (folders.hasNext()) {
            folder = folders.next();
        } else {
            folder = DriveApp.createFolder(dropbox);
        }

        var fileName = uId + uName + "profile_pic.jpg";

        var contentType = "image/jpg",
                bytes = Utilities.base64Decode(uImage),
                blob = Utilities.newBlob(bytes, contentType, fileName);
        var file = folder.createFile(blob);
        file.setSharing(DriveApp.Access.ANYONE_WITH_LINK,DriveApp.Permission.VIEW);
        var fileId = file.getId();
        var fileUrl = "https://drive.google.com/uc?export=view&id=" + fileId;

        sheet.appendRow([uId, uName, fileUrl]);

        return ContentService.createTextOutput("Success").setMimeType(ContentService.MimeType.JAVASCRIPT);
    }

    function read_all_value(request){
        var ss =SpreadsheetApp.openById(SCRIPT_PROP.getProperty("key"));

        var output  = ContentService.createTextOutput(),
                data    = {};
        //Note : here sheet is sheet name , don't get confuse with other operation
        var sheet="sheet1";

        data.records = readData_(ss, sheet);

        var callback = request.parameters.callback;

        if (callback === undefined) {
            output.setContent(JSON.stringify(data));
        } else {
            output.setContent(callback + "(" + JSON.stringify(data) + ")");
        }
        output.setMimeType(ContentService.MimeType.JAVASCRIPT);

        return output;
    }


    function readData_(ss, sheetname, properties) {
        if (typeof properties == "undefined") {
            properties = getHeaderRow_(ss, sheetname);
            properties = properties.map(function(p) { return p.replace(/\s+/g, '_'); });
        }

        var rows = getDataRows_(ss, sheetname),
        data = [];
        for (var r = 0, l = rows.length; r < l; r++) {
            var row     = rows[r],
                    record  = {};
            for (var p in properties) {
                record[properties[p]] = row[p];
            }
            data.push(record);
        }
        return data;
    }

    function getDataRows_(ss, sheetname) {
        var sh = ss.getSheetByName(sheetname);

        return sh.getRange(2, 1, sh.getLastRow() - 1, sh.getLastColumn()).getValues();
    }


    function getHeaderRow_(ss, sheetname) {
        var sh = ss.getSheetByName(sheetname);

        return sh.getRange(1, 1, 1, sh.getLastColumn()).getValues()[0];

    }



 */