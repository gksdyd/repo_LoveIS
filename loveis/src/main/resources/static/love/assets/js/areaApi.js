var accessToken = 'none';													
var errCnt=0;
				
// getAccessToken();												
function getAccessToken(){												
    jQuery.ajax({																												
        type:'GET', 																											
        url: 'https://sgisapi.kostat.go.kr/OpenAPI3/auth/authentication.json',													
        data:{																													
        consumer_key : '509a370928e2438893e8',																					
        consumer_secret : '138f0db2a07a40a68388',																				
        },																														
        success:function(data){																									
            errCnt = 0;																									
            accessToken = data.result.accessToken																									
            updateArea();
        },																													
        error:function(data) {																									
        }																														
    });																		
}			

function geoCoe(cd){																						
    jQuery.ajax({															
        type:'GET', 																											
        url: 'https://sgisapi.kostat.go.kr/OpenAPI3/boundary/hadmarea.geojson',														
        data:{															
            year : "2023",												
            adm_cd : cd,																					
            low_search : "0",
            accessToken : accessToken																							
        },																														
        success:function(data){																								
            switch (parseInt(data.errCd)){																					
                case 0:																																								
                    var resultdata = data.features[0].properties;																				
                    var x = resultdata.x;																				
                    var y = resultdata.y;
                    convert(x, y, resultdata.adm_nm);
                    // console.log(cd);
                    break;																				
                case -401:																						
                    errCnt ++;	
                    if(errCnt<200){	
                        getAccessToken();
                    }	
                    break;																					
                case -100:
                    break;
                default:	
                    break;																			
            }
        },																														
        error:function(data) {																									
        }																														
    });																		
}

function convert(x, y, name){																						
    jQuery.ajax({															
        type:'GET', 																											
        url: 'https://sgisapi.kostat.go.kr/OpenAPI3/transformation/transcoord.json',														
        data:{															
            src : "5181",												
            dst : "4326",																					
            posX : x,
            posY : y,
            accessToken : accessToken																							
        },																														
        success:function(data){																									
            switch (parseInt(data.errCd)){																					
                case 0:																			
                    var x = data.result.posX;																				
                    var y = data.result.posY;
                    $.ajax({
                        async: true 
                        ,cache: false
                        ,type: "post"
                        ,url: "/love/area/AreaLoveProc"
                        ,data: { "longitude" : x, "latitude" : y, "ifcaName" : name.split(" ")[1] }
                        ,success: function(response) {
                            
                        }
                        ,error : function(jqXHR){
                            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
                        }
                    });																																					
                break;
                default:	
                break;																			
            }																					
        },																														
        error:function(data) {																									
        }																														
    });																		
}

function updateArea() {
    for (let i = 11010; i < 40000; i += 9) {
        geoCoe(i);
        if (i == 11250) {
            i = 21001;
        } else if (i == 21510) {
            i = 22001;
        } else if (i == 22520) {
            i = 23001;
        } else if (i == 23520) {
            i = 24001;
        } else if (i == 24050) {
            i = 25001;
        } else if (i == 25050) {
            i = 26001;
        } else if (i == 26510) {
            i = 29001;
        } else if (i == 29010) {
            i = 31001;
        } else if (i == 31580) {
            i = 32001;
        } else if (i == 32610) {
            i = 33001;
        } else if (i == 33590) {
            i = 34001;
        } else if (i == 34580) {
            i = 35001;
        } else if (i == 35580) {
            i = 36001;
        } else if (i == 36680) {
            i = 37001;
        } else if (i == 37630) {
            i = 38001;
        } else if (i == 38600) {
            i = 39001;
        } else if (i == 39020) {
            break;
        } else {
            i++;
            geoCoe(i);
        }
    }
}