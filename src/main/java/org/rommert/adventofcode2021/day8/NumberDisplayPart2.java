package org.rommert.adventofcode2021.day8;

import org.rommert.adventofcode2021.InputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NumberDisplayPart2 {

  private static final String input = "" +
      "cdafg dage fgdaec cdbfgae cge gcbdfa fdceb gfceab ge ecfgd | eg eg dfecag ge\n" +
      "efgcabd edacgf gfaec deg dg decga dcfg dbgafe bfgcae abced | deg baced fegac aefbcg\n" +
      "egacf dfbeg dec efcgd cfad cfagde fabcgde dc agbecf bgdeac | gcadef eacgbf egdfb bgdfe\n" +
      "dc cgefb cdf bdfgc adbc gafdec cbagfd gfbad dbcagfe gbedaf | cd cd agfbd bdac\n" +
      "dcbafg gadeb faegb cfbga ef gfe feca aegfbc cebgdf dfabceg | faec ceaf bcedgaf cfbga\n" +
      "bcf adbcef cbgdfe dbceag gcdbe dcafg egadcbf fb dgfcb gebf | cbgedf cbfdae aegbcd bf\n" +
      "dfge dg fbgce ecgfdb fcabdg bafgec cdg cbdae gecbd ecgdabf | eafcgbd gd cfgbde cdg\n" +
      "fadbg badfe faebcd gab ebfgad bfceag gfbcd gade adcebfg ag | gbfdea aedg becfad aedfbcg\n" +
      "gcfbd dga bfdcga gafdbec dcafg defac ag adbfeg acbg bfdecg | dbgeaf ga dagfeb fagdc\n" +
      "efdab cgadf cbafd fgbcda ebgfdc cfb bc gcba cefgad gfabcde | cagb fcdage bc dacfgeb\n" +
      "acgfe gcedbf fge dbfacg bgcfa fcbaegd ebga eg feacd egabfc | geab fgabc cfaegb egfcba\n" +
      "cef egcbd fbage cf bacf fecgba fbadegc fgdeab fcbge daegcf | dfaegc bedgc ecbdg becgf\n" +
      "acdb bdegc fgced dgb fbdgace agefcb cegdab dbgafe bd baegc | fedabg egcdba bcafeg fdageb\n" +
      "adbfce bfgc dfcebg efbdg cbefdga bdcge cb afgbde gceda cbd | ecgad egfdab dbc fgcb\n" +
      "dafgbe ecafg edcbfa fdcg cef fgade cf gecab gdacebf cedfag | bdcefag gfdea eacgb cf\n" +
      "acedgf becdgfa fgcae fdgac fdcgb aged gcfeba ebfdca ad adc | begfadc dfgac efagc fgcade\n" +
      "gefbd beafgc dabge fgad ag bdcegf agfebd gba acdeb dgacebf | ebfgda egdab febgd adgf\n" +
      "cdage gcebfda dea bceag de bfcdae egbd egbdac gebafc cadfg | ebdacfg agcbe adbcegf degacb\n" +
      "becf fcd cabdg gefdab dbgcf ebcgfd cf bgdfe edfcag egafdcb | gdcfbe gefbd eagfcd gbcfd\n" +
      "ec dagcf debag eagdc cfabed eca begc bfeadg dfcbaeg bgeacd | dcagf decgba eac cefabd\n" +
      "gace egadbfc bfecgd dce beadf decfa gcfdab fcdaeg ec afgdc | afecdg badfcg cfaedg adegfc\n" +
      "cfbge ac cea fdeab adfbgce fabceg dgfbce abcef acgb afecdg | bagc cfebg fbaec eca\n" +
      "gfdcba bcf ecfdg bc baefgd efbgac caeb aegfb cebfg badfegc | cfb cfb cfdge cbf\n" +
      "acg cagbdfe ca fgcadb agcebf cegfd acfgd dacb bdfga feadgb | edfgba fegdba gabfce bfegda\n" +
      "eabgfc gfeac dgaeb ecgdfba cba eabgc dfaegc cb cfgb cdbeaf | agfec ecfdga aegbc bedga\n" +
      "defgac eabdcf be faedg beag bgdfe gdebfa ebf bgcfd bgdceaf | dfgcb eb fdcaeg ebf\n" +
      "db cfdbaeg baecgd dfbega bdgaf afebg febd adfcg aebgfc bdg | fbaegc fbgae bfde abfdg\n" +
      "defbg cbdfeg dcbegaf cbdf cfe begca aegcfd ecgbf fc agdebf | bgfaed defagc efdgb cdfb\n" +
      "ecdafb dbf bgdea abcf fdaegc dabfe fgecbd fb deafc fdacbeg | afdbce facb bf fb\n" +
      "fa edfabg bfacd gcefdba fba agfc cfedb fagcdb agdbce adbcg | efcdb edgbaf cadgbf gcfa\n" +
      "cfb gcfbea bacgfde bc geadfb badc bedfa edbfc ebadcf gdfce | gbfdae fcbedag gaefcb cebdf\n" +
      "cfd gdeac ecbfagd cagdf fagcbd dafb egcfab bfgcde fagbc fd | fdba cagdfbe dafb dcf\n" +
      "fcageb fa gfa dbfa begdfc dcega gdbfe eafcdbg fdaebg afged | faedg gfa afegcb eabcdgf\n" +
      "feabc gf agdbe gedbaf fgbd gfbea becagd fge gadcfe cdeagfb | fgabed gf afcdeg aebdgc\n" +
      "dfebc gedfcb bcgfd fde dgbe afcdge dcgfab ed befca fbcaedg | bfcged fde febca dafbgc\n" +
      "ebafcd acbd cdebfg ead ecdbgaf edacfg efbag da fdebc fedab | cgbeadf bacd bcedfg gbcefd\n" +
      "beadgc bd gcfba adgce cfedgab bgd cgfdae agcbd gadbfe bdec | gebfad dbegfa cbafg dgfabe\n" +
      "fdecagb bdafce gbcfde egcbd ea eadg cbgae gbceda agcfb aec | cfabg cgfba cgdabfe cafgb\n" +
      "ca fegabc bdac gecdfb feabcd cea eafgd aefdc dgbaefc edcfb | fbdgec aec cbad adbc\n" +
      "baegfdc egb fcage bfcged bfdea gafecd gbac bg caegbf fegab | cfadebg bcgdfe gcbefd bgefa\n" +
      "fdabge faedcbg dfbea gfcade dfe de adbgfc egdb eafbc badfg | dabgf fcagedb gdabef de\n" +
      "bagcdfe dfaceb egdafb facegd cedbg ga dga bgaed fbag bdfea | bedcg afbged dfbea dcbeaf\n" +
      "cdbef caebf defabgc ecfbag cd dfc aced dbegf fcdbae bdgfac | ecgbfa cd dc ecfagb\n" +
      "facbdg dfgacbe bfcgd bcgedf bgead dec gfdeac fecb egbcd ce | gdbaefc bagcfed bcgdfea cdgbef\n" +
      "abcef ebfdc dfagcb dcbgef ba daeb ceagf dcaebfg faecbd fab | dabfce abf fgdaecb dfbce\n" +
      "cbe abecgf be gbacdf dacfebg eabcf aegcdb gabfc gfbe fdeac | ebcaf cdbfeag fcdea egfb\n" +
      "gfad degab fcgdbe aeg bfedg abdgfe gefacbd ga badce fagceb | cfbged efgcab bfdeag fdbeag\n" +
      "fgadbce edbag dgbcf dabfg fa fdaceg bacedg faeb bfadeg gaf | fa abfe bfdgae gdafcbe\n" +
      "afde cfadbg gcdbe bfdecag ea efbacg abe cfbda debac dacfbe | fcbega ebadc eba cafdeb\n" +
      "cefgad bdcef acdbfe edbgfc dae beaf bdacefg ea becda bgcda | gdacef adbefc bcgad dceafb\n" +
      "dfbaecg eagfd cf acdf bcgea bcfdge efc dacfge efacg gbadfe | fc fce fecdbg caefgd\n" +
      "afec daebgf dfgcab fcgeabd gef afcgb dcgbe ef gaecbf fecgb | fe cgfab debgfa fgceab\n" +
      "cgafde afbgdce bgafc gabcfd bgdf badgc gf aecdgb ecabf gfc | acgfb gfdb cagdef fcg\n" +
      "fdgec bgcad bfec gbefdc dbf gfdcea fcbgaed bf bfgdea gfdcb | ebcf dbcgf dbf bfcgd\n" +
      "dfceb cdaf bdfage afebc bgfdec fa gbcfdae fea gbeca faecbd | fcgebd fbgaed afdc fa\n" +
      "fgcaeb fcag fa bdace efbcg gdfbaec fab dfgbec abfce fabdge | gcedbf cefgdb ebcdgf efdgba\n" +
      "afegdc fegdbc gfeab cdfb dcegba db bde degfc bafcedg dgfeb | gabcefd cgdbfe bde afgbe\n" +
      "dg gbd adgfcb cfeagb bcfag bcgfd gdaf cbfed gcfaedb ecabdg | dfbgc dg cdfbe dbgfc\n" +
      "cebgfa gcadfeb afcdeb dagfb facdg adbfge bdf db egdb ebfag | dbgfea db bgdefca dcfabe\n" +
      "adbf fgd bcegadf cgfead egbfd bgeda bdceag bgfce fd aegbdf | gedabcf fgbde cbegf cdfgea\n" +
      "ga cgbfd decba gaecfb dfbcga cbdgfe cbdegfa fgad agcbd gab | dagf egfcab bgcda cdfbag\n" +
      "egbfa geca fabcg edfba fge fdcagb cgebfa dcefgb bgdface eg | egafb fabeg fbade aefbg\n" +
      "egcad fadbge fbgd faebg acdbfe edabg bafcgde dba db fecbag | dcgae cbeagdf dacbfe db\n" +
      "ad dcbag cegdabf cgbea feacgd adeb debcga acd agbcef dfbcg | dagcb aegbc cad dgfcb\n" +
      "cebad cfadgbe ecd gdcbae cage cgdbef ce cgdba gcafdb adebf | adecb cfgdab cedbgf daecb\n" +
      "fagceb cbdef cabgfd gadbc ae dfcgeba gcebad bae aegd abdce | abe bae bae gead\n" +
      "efgcab eagdf de ead bfde cdgabfe ebfdag fbeag agedbc gfdac | fcgad eda dgfac eda\n" +
      "gfbeadc cgeafb gdecf bcedgf dfgca cdagef bgafd afc ca cdae | fecgad dfgce cfebga gdfba\n" +
      "fc egfbcda dfc degfca fgdbc dafbg febc dcebg cebgfd ecbagd | eagdcf efcb agbfd bfecdga\n" +
      "db acegb fdbegac dgefa gfcdeb egadbc cdba bde geabd becfag | dbfeacg adcebg cgedab decfbga\n" +
      "ecg acfgde cedb dfecbg gcfdba ec cdbgf cfebg efdbcag fegba | ecbd aedcfg cgfbe gec\n" +
      "fgcadeb ecfagd egfda cd cdf edac fecdg fagdeb gfdbca gcefb | dfgea adgebf dbfgeca cfagbed\n" +
      "cfgedb fgdba cgdef edgfa ea fbaegc age decgaf aced gbedafc | aecdgf dfbag aced dfecbg\n" +
      "de agebc bcdgf fagbec adbegc cbged daec gdefacb deb gdfeba | gdbace aebgc begfad adcbfeg\n" +
      "fdeg cfbgda dcaef dceab gdefac dcf cgafe begdcfa cfeabg df | fcd gdabfc bgefac egcaf\n" +
      "efgbd cgfbe gcb egabcdf debfag cg cgdbfa bceaf cdge degfcb | bfegad cgb gebdfc decg\n" +
      "aefbd bfaec bdgcaf dgeb de fgceabd fgaedc egbdfa badgf fed | bgafde fbaed gdfab gbed\n" +
      "bcdea defagbc fdce cbfade fagbd egcfab cf adgebc cbf dfbca | gbadf dcef dcefab gbcfae\n" +
      "abdc cb bgc bacfeg fabgd cfdeg fcdbg gcdebfa dgaebf dgbfac | gbdcf fcbadg cbg ebagfd\n" +
      "bfgca gfbadce dbcag ecabf cfgead fcg gf edabcf fbge afgbce | bgcda cdgba gf bfcgaed\n" +
      "afgbde cfdgb afbcge gbfec ec cfe dgface gfbae fcagbed beca | cbgfd fdcbg gadcef cbdgf\n" +
      "bfdga gaed dbagfe cgdbef edfba bcaef de gbeadcf gdfacb bed | ed aegbfd adbfgce de\n" +
      "adcb ecgafd ba abf bfedca gdbfae edcaf bgaefdc fcbeg fbeca | efdcga ecgbf gcefb dbeafcg\n" +
      "ac bdecf bac daegb bdegca bcefga gacd febdgac abdgef badec | cgda cagd bgaefd dagefb\n" +
      "gcd ebcd cgfbade dabgfc gbdcfe fdcge gceabf dc gfead ebfcg | gfdbce baefcg dceb fcebg\n" +
      "ceg cfgae facbde ge gcfba bcdfega edacf fcgedb dage cdegfa | dafce ge cdfae fbdeca\n" +
      "decgf fadeg fgaced febda eag fagc ag bgadec fegbdc bafecdg | afdeb badfe aecfgd adefg\n" +
      "da bdeca fdgbce afdbegc efbac gacedb fbdcga acd cdegb gdea | gdea facbe cfeab bedca\n" +
      "ebagdc cdaefg cebfg fadg gd ecgfd dge afedc ebadcf dfbcage | eacdf dagf cgfbe ebfcg\n" +
      "fdbcea ag fag fegdba ecagfd bdgcf afecd afgdc cgae efgabdc | fdaebcg caeg cfgda dgfbc\n" +
      "ea cabgde acbgfe cefbgda acde edbcg aeb ebfgcd dfgab aebgd | cafbge dgfab cdgbe deca\n" +
      "acfd ebgfcad fecbgd dcgabe ca eagfc eac gefab gfdce cgfdae | cgaebd fcad geafc egdacf\n" +
      "fdecab bf fdcb egdaf cgadefb edacgb ceadb daebf afegbc efb | dfebca cedbaf ebfcga dfecab\n" +
      "badef fbedag daebcgf bgcfa dacgef cfadeb dac dcbaf dcbe cd | afcbd dbec cebdafg bacdf\n" +
      "fagedb acbge fceabg cegfb bagcde aecf cfbgd aegdcbf efb fe | bdfaeg fe febagc bceag\n" +
      "efagd dfacg fgcb dgc cefbad cgabde cadfgb dbafecg cdbaf gc | caebdf bcaedg afgde dfcga\n" +
      "egcbf gbdafe fd edf gfecd gcbdae aecdg fgdcea gacdbef cfad | cfbeg cedga fadc facd\n" +
      "bdfceg acd dfbce bfdag ac adcfb daefcb egadcf cbae edfabcg | dbeacf fadbg abecdf bedcf\n" +
      "bcf badcf ecdaf eadgbcf abgf defcbg bf ebgcda bcgda acbfdg | fcb decfa fcb facbgd\n" +
      "da cda adceg bead abgcfed ceagdb gcbed cfgae agdbfc dfbegc | fgdbec cdgfab gebadfc fgcae\n" +
      "gbaecf debfg dbaceg badc eagdb ab abfegdc dcgea cdefag bga | ab cedbafg afcged cgdae\n" +
      "dacfbge dafbc baegdc fagbde dacgb fbcg bdfcga fb abf fdace | acegdb ecfad badfc cadfgb\n" +
      "acbgf gadefb cd egcfad bedc cad cadbg cagbdfe gdeba bdgaec | gceafd gbfac egfabd dcbe\n" +
      "fdbgcae feacgd gcdfe cgdefb gabfe defgb dgb db caegdb dfcb | cgfde cdebga cgdaef gedcaf\n" +
      "ae gacbe facbge cbdefa eca acfdbg afbgc gecbd gcaebfd agfe | ebafgc fgae bcaeg ecgdb\n" +
      "bfdceg badecfg bgefda ba bfa cbda fcage cdeabf fbaec efbdc | bcda fba egacf efabgd\n" +
      "gbc bdgce gcbdefa geac cg bedca dfbcea dgcabe afdcgb gedbf | dfbcea fcdbea gc dfabec\n" +
      "becdf gaedcb cgedaf aegcf fagcdeb gfab gefabc cebfa bca ab | ab gaebfc dceagb fcbdaeg\n" +
      "eagbc acfdge db gfadc fdgb fbagcd dba dcagb gecfbad efbdac | fgbd cgdeaf cdgaebf eagcdf\n" +
      "ga eafg adbge gabdecf cbade bdgfea afgbcd gfbde gab cgedfb | gaef cabed fbgdae agb\n" +
      "efabc cdeaf dgcfa ecbafg ed dagfbe bfadec egdbfac cedb efd | efd bfeca bdcfae de\n" +
      "bagf fb cfeba gcbade cgaeb adfce befgcd cfb defabcg gcfaeb | daecgb bgeca egcab febdacg\n" +
      "dfceg gfcedb bdfea gb bfedg cgdb afgebcd fgdace cbfgea fbg | gb dcbg bedfa gfacbe\n" +
      "gecfab dacgf cfbgdae afdcbg afde cea gcebd fgecad gcdae ea | edacg ea ecafgd gacbef\n" +
      "ag afg bgcdef abfec begdf gadfbce bfgae egbafd egda gadcbf | gafdeb ga bafec dcgbafe\n" +
      "cgadf cegdbf egaf dga efcadbg dfcge ag cabdf eagfcd cegdba | bcafd gdcfbea cdbaf ag\n" +
      "dcbge fdgabc cegba aegfc gfebdc ecfdbga aedb cgebad ab bga | ba aebd bgaec cdbeg\n" +
      "cdg fgdb adfecb gdabfc cagfd fbadc febcdag cdebga geafc dg | gd deabfgc fbadgc fcbdae\n" +
      "debgac fceadb eg gbcfeda gfabde efgd gfbac feadb gbe baegf | baedf edgf cabdeg befdca\n" +
      "cf bgcfe cfg cdgabe cbdf dcfegba bgafe cdefga gbecd edbgcf | dbcf fcbeg cbdf eagbdc\n" +
      "cbefag gbe agec cbgfdea gdbfa facbed dcebgf eg eafbc ebafg | baefgc bdfeca eacdgbf egac\n" +
      "gfd bfgec dfeagbc fedgbc fd cdbf fbeagc efbdg ecgadf daegb | dgf dgf ebdcagf eagbd\n" +
      "fcedbg bcde be fgbecda bfe fadecg edgbaf gcdef becfg fbagc | efdcgb bfgead ecbd fcedg\n" +
      "fbdgae gefabdc bfgdca gbf fcabed cafbg bcdaf gcbd gb agfec | acgdfb abgfdc bfg fcaeg\n" +
      "cfbegad gdfcb bfgde bcdegf egcb bc adcfg gadbfe beafdc cbd | fgadc adecbf gdbefc gbdfe\n" +
      "abgcf ecabdgf dba acgdfb gfebd dfac adbgec da afgdb cbaefg | fbagd dbegf begdf defgb\n" +
      "bdecfag ef bcefda adgce feagbc gbef gfcae fcgab afe fdabcg | cgfeab cfgea bdefca afe\n" +
      "dg cbgeda bgdfa egfd dgb gebadfc febcda gcbaf deabf gaebdf | bdfgcae afgcb dgb bceadfg\n" +
      "cfagedb eacgd baecfd cbafd ef eaf bdfe fgbcad dface febacg | afe fea fdcaeb cedga\n" +
      "fdageb egbcf ec ceb dgcbf gfeba fegcab acef bgaecd cebadgf | bec dgafeb faebg fcbge\n" +
      "agecbfd fcd gbedac dbcfag dfacg df gbcda fcaeg febacd dfgb | dcf gcfea acegf gdabfc\n" +
      "dafebc gbfa agdfc fbcgad fgceadb cdbfg bf bfd adgfec bdcge | fb adbfce feacdbg gafdc\n" +
      "caebgd bgcf gabef bf agbec abfcged bgeacf ebf edfag cfdeba | gaebc fdega ecgba egdfa\n" +
      "deba eb bfe bfaedc ebfdc cgdfae gdcafbe cbeagf bgdcf dacfe | gefcab gbcaefd fbgcd bade\n" +
      "aefcbgd afcde cea aefcgd dgefba efagd ca cagf gcdabe edcfb | adfgbe fgead abfged afgde\n" +
      "cadge fbagec ag ebcdgfa dagf afedc age ecdabf cbdeg gcfdea | eag cafde ega gfacbe\n" +
      "cbgefda acd afgdeb dc fdgab dcbfae gdbc cdbgaf acfeg adcfg | fgdac efdgba dfcag efbgcad\n" +
      "cegad bfgcd baegfc facgd aebgdc af fag feda edafcg cgfbdae | af agdfc gbdafec gfcda\n" +
      "aecgdf ecgfb bfdea cfbaged dgbfce cdf gdcb dc bcdfe aegfbc | dcf gabcef ebcgf fdc\n" +
      "bcafe bfcdea febga ac cfa cdeagf dabc cfdaegb cfedb cfgebd | bedfc dbfgce dcfeb egfba\n" +
      "cgfedb afcb fdc fc bdgcea gacdb gedaf ceafbgd cfgda adcgbf | cgadb cafb dcf adfeg\n" +
      "ebcf def cgaedbf becad bagfd egdcab fgaecd ecfdba ef dfbae | eabfd becf adcgfe gbdfa\n" +
      "gcfabe gcabe dfgca beagdf fbg abfgc bfce bedcag fb fbcagde | bgcae bfce fb bgfeda\n" +
      "dca ebfdacg cgbd gabfde cgfad gadbcf cfeabd fgeac bgdaf cd | cdbg cad dac cd\n" +
      "ebfgadc efgbad bdfceg cbdfg gecb bfedc fgb gb dafcg fedbac | fbg fbg cgeb gebc\n" +
      "ecadfb fdgceba gdebac dea fegdc feagcb ebafc fadce da fabd | bfcega cbaedg cbdeagf bafec\n" +
      "dbf fdaceb cdgefa bf febgacd aefb fdbca cdefbg dcagb edcaf | bacdf dfacb cbfeda aegcdf\n" +
      "gdbef bae edbfcg bfcag agde gebaf gaebfdc cfdabe afdebg ae | defbag afdceb gebfa agbfed\n" +
      "edabf dgbcf bce afecgb degc dbfec bfgcaed ce bgdcef cdfbag | gedc cbfed dfbgc cdeg\n" +
      "bdcfga fag dgafc fg dacbf bdafec cfbg fabdgec eabdfg eagcd | debcaf eacdg geabfd gbefad\n" +
      "cgfda badfc ba gcfdba acfbdeg bcga ebfdag faegcd abd dcfbe | afcdbg acbg ecdfb bdfac\n" +
      "bcdae febgc egdbafc fd gcfd bdefc bcedgf fbdega fde fabegc | dfbgec efcabdg cfbge gdeafb\n" +
      "bfedacg agfdec deg gd decbf ebgcd eadgbc gbfeca bceag gbad | gd ceagb gd gdcafe\n" +
      "acbgd dbfceg cegfdab dbaegc aebcg ecb be dcbgaf egcfa eabd | adeb bec begcdf gfeac\n" +
      "gda debfa gdfe gd cfdgbae bdagec badgfe bgdaf cbdefa fcabg | geabdf abefgd dag ebdaf\n" +
      "febgcd cdaegf bf fabge edafbgc gfaedb baegc ebf fdage fabd | dgecaf badefg geafd dfeacg\n" +
      "adegfb cbgefda dcag agbfdc gbcfa gadfb bfecg ac acdfbe cba | bfgca facgb fgcba ecfdagb\n" +
      "adcf fbecdga dbeagf ac eca gefdac edcag cfbeag edgaf cgebd | dcfa cadfge adegc gcaed\n" +
      "acbdgf gaecfb ad cbdfaeg gebfa gcbed efda gad baegd afedbg | gbfea aegbd adg da\n" +
      "da abd dbgfc dgca gadbf bcgedaf dfbacg fbeadc bfecdg gabef | cedbagf dfcbg gbdacf gcbfd\n" +
      "agcdf cbfgd da dacb cagfe abgdfc aedfbg agd edcbgfa fegcdb | da ecfag aegbdf adgcbf\n" +
      "cd aegdb acfbe bgdc ebacd dbfgea feacdgb agfdce dce dbceag | acdegb gbdc gacdfe dcbg\n" +
      "abc deacbg aecf cgafb dcbgf cgfbae gbaef ca geabdf gcbafde | cgbfd fcea abfeg feca\n" +
      "eagcb edacgfb abfegd abedf aefdcb gfda dcbfge gbafe fg gfb | cbfade acdebf gdfceb bgf\n" +
      "dgcab aedgb gbdcfae acbe beg gebfdc gdbcfa fdage dceabg be | gcedab baegcd bdega beadg\n" +
      "gd dfcgea afdgeb cdfae befacd beagc ged daegc afebdcg cdfg | cdega dcefab gfaebd ebgac\n" +
      "gfcebad gfeca bdcae fbeg cbgafd bg dcagef gab gacbe fabgce | ecbad abg agecf bg\n" +
      "fegdc bdag gbedac dcfeba gfeabc ebfgdac gedac ga ceadb cag | fecbag ebdcaf bfcega aegdc\n" +
      "deabcf dcfa cd ebgfda ecabg aecbd cegfbad dbc cgfedb faebd | gdcbfe cbd befad dcebfg\n" +
      "gedcab bagdfe bf aecdf begf aefbd dbf eabdgcf bfdagc eagdb | fdbea fb cedfa gafdbe\n" +
      "cgfd afecg cedgfa eabcd aefgcb afdgceb dfa fd eafcd debfga | afbgec gadebf fdcg acfed\n" +
      "cdagbe agefb fedabc fagcb gfebdac fgde baegd fe eadbfg feb | gebfa aecbfd fe bedag\n" +
      "adcfb bdcga df daf gdabfe cafdbe cbgfae decf becfa cbgadfe | gdbfcea fgebad fad bdacg\n" +
      "dcgef agc cfbadg ga afgec bcaefd efcba abeg cdeagbf aecbfg | dcfge fbgcae bdgfac fcgde\n" +
      "adcge gdcbaf bad cgdeaf bced abfge dgaeb db aedbgc ebdafgc | acebgd bad bad bdaeg\n" +
      "bgd adfcge gdacb fadb bd degfabc bcaeg dcfag gefdbc gbdafc | bceag badgc db cfdgbe\n" +
      "bdf cbadef ecafd bfacdg bd cbde baefd afcdeg ebgaf edagcbf | gdbcefa dcagfb bfaed dgacfe\n" +
      "cbdaf efbd ef afbec caefgd dagbecf gcadbf cbgea dbeacf aef | afbdc cgfdae caebg adgecf\n" +
      "gbfec ed fgebdc defacgb deb gedc cfebd fedbga cabdf abcefg | gedbfa dfbegac bgcef cbfed\n" +
      "dgc aedcg aedcf gd acegdfb cageb edgb dgcafb cbegfa ceabdg | egbd beacgfd aecfbg gd\n" +
      "abf gdbaef abgfc bf gcafe bdagc dbcagf cadbge bdfcgae fbdc | gfcdab fbgca adbegc cfbd\n" +
      "becfdga eac acedf cgbeda gafdec fabcd egfda ecfg dgaefb ce | cfead afdec gafed bfcda\n" +
      "bdgceaf febg bfacdg feacb gaebfc aebdc ef cfbga facegd fec | bcfga ecf cfbea acfged\n" +
      "baf geacf ab cfdebga cfbgde fgcbd gfbdea dbac gcfab adbcfg | bcdegf bgcdfa dgbfca gfcae\n" +
      "cd ebgcf ebdc dagbf fbgced fgecab cgd fedagc fbcgd fgecbad | fbcge bdafg cfdebga cbed\n" +
      "dfbcag geafc agdec da gad dgecb cgbfae faed edafgc adcgfeb | cagefbd dfbgca gdceb edcga\n" +
      "dfegac aegbf cg ceg agceb bafgde cebda fgbc cebgaf aedbgfc | bfcg efcbag ceafbg fbage\n" +
      "acdfg egd fcge fecdga ecbfgad eg aedcb eadcg gfebad adcfgb | ceabd afdegc aecdg gfbaed\n" +
      "fcbadg faebdc ga edga fcegb geadbf gefab bafde afg dcegbfa | fdceab bfgec fag cgefb\n" +
      "fbge bfeacg edabc dgbfca egdacf beacg gae fceabgd ge fcabg | aebgc abefgc bgef gcafb\n" +
      "cbe fedgb aedc ecbfga ec dabgc bfegdca degbc cgafbd bdeagc | fbedg fcbaedg dgcbe bce\n" +
      "gbdc cb edcfa bagfd agfedbc agfbde dfcab cfb gebafc cagdbf | bcf afgdb dbcfgae cb\n" +
      "bgdae fbagde cegdbfa aegfb fb dbfe gbfdca ecdabg fgb agecf | gadeb bgfade fcbaedg gabed\n" +
      "bfacdg ebcfda cgdfeb edbfgca feadg cfa gabc cgdaf ca cfbdg | ebgfdc bafgcd fgade afc\n" +
      "cgedba abcfge ade fgdbae edgaf egbaf edfabcg bfde ed gcadf | cfdga gaedfb adegcb fgecab\n" +
      "fbdcea bgdcf abfcgd gcab dcgbefa dafcb afbgde fgedc gdb bg | efcabd bcgdf badegcf acdfgb\n" +
      "ebdf dcafgb afdecg ebgca adbfg fe efg bcfdage eabfg agbfde | agcdfb badgf geadfc abefgd\n" +
      "daebc bedcfa def eacfg eafdcbg bcdf edgabc df dfcea gaedfb | fecadb gedbfa dcbea egbfad\n" +
      "gf gdcf cdabf bceadfg dfcgba cbagfe gdeba fbdag gfa dbefac | gedab cfgabe cfadb gbdaf\n" +
      "ced edcgabf dgfceb cdbg fbceag daefg ecdfg cd cfbge cabfed | ebdacf dec dce dacbegf";

  public static void main(String[] args) {
    List<String> signalLines = new InputParser<String>().convertInput(input, Function.identity());

    int counter = 0;
    for (String signalLine : signalLines) {
      List<Integer> numbers = processSignalLine(signalLine);
      String number = numbers.stream()
          .map(Object::toString)
          .reduce("", (total, value) -> total + value);
      counter += Integer.parseInt(number);
    }
    System.out.println("sum of secrets = " + counter);
  }

  private static List<Integer> processSignalLine(String signalLine) {
    List<String> signals = getSignalsFromInput(signalLine);
    List<String> secretNumbers = getSecretNumbersFromInput(signalLine);

    Map<String, Integer> numbers = determineNumbers(signals);

    return secretNumbers.stream()
        .map(NumberDisplayPart2::sortSignal)
        .map(numbers::get)
        .collect(Collectors.toList());
  }

  private static List<String> getSignalsFromInput(String fullInput) {
    String signalsString = fullInput.split(" \\| ")[0];
    return Arrays.asList(signalsString.split(" "));
  }

  private static List<String> getSecretNumbersFromInput(String fullInput) {
    String signalsString = fullInput.split(" \\| ")[1];
    return Arrays.asList(signalsString.split(" "));
  }

  private static Map<String, Integer> determineNumbers(List<String> signals) {
    Map<String, Integer> knownNumbers = new HashMap<>();
    Map<Integer, List<String>> unknownSignals = new HashMap<>();

    for (String signal : signals) {
      List<String> currentListForLength = unknownSignals.getOrDefault(signal.length(), new ArrayList<>());
      currentListForLength.add(signal);
      unknownSignals.put(signal.length(), currentListForLength);
    }

    // some lengths only have 1 nr
    String signal1 = unknownSignals.get(2).get(0);
    knownNumbers.put(sortSignal(signal1), 1);

    String signal4 = unknownSignals.get(4).get(0);
    knownNumbers.put(sortSignal(signal4), 4);

    String signal7 = unknownSignals.get(3).get(0);
    knownNumbers.put(sortSignal(signal7), 7);

    String signal8 = unknownSignals.get(7).get(0);
    knownNumbers.put(sortSignal(signal8), 8);

    // nr9 is the only 6-char signal that contains all letters of the nr4 signal
    String signal9 = findSignalThatContainsOtherSignal(unknownSignals.get(6), signal4);
    knownNumbers.put(sortSignal(signal9), 9);
    unknownSignals.get(6).remove(signal9);

    // nr3 is the only 5-char signal that contains all letters of the nr7 signal
    String signal3 = findSignalThatContainsOtherSignal(unknownSignals.get(5), signal7);
    knownNumbers.put(sortSignal(signal3), 3);
    unknownSignals.get(5).remove(signal3);

    String signal5 = null;
    String signal6 = null;
    for (String signalWith5Char : unknownSignals.get(5)) {
      String signalThatContainsOtherSignal = findSignalThatContainsOtherSignal(unknownSignals.get(6), signalWith5Char);
      if (signalThatContainsOtherSignal != null) {
        // The nr6 signal is the only 6-char signal that contains all chars of another 5-char signal. And that 5-char signal is nr5
        signal6 = signalThatContainsOtherSignal;
        signal5 = signalWith5Char;

        knownNumbers.put(sortSignal(signal5), 5);
        knownNumbers.put(sortSignal(signal6), 6);

      }
    }
    unknownSignals.get(6).remove(signal6); // remove these...
    unknownSignals.get(5).remove(signal5); // ... so we know which numbers are left

    String signal2 = unknownSignals.get(5).get(0); // nr2 is the the only 5-char signal left
    knownNumbers.put(sortSignal(signal2), 2);

    String signal0 = unknownSignals.get(6).get(0); // nr0 is the the only 6-char signal left
    knownNumbers.put(sortSignal(signal0), 0);

    return knownNumbers;
  }

  private static String findSignalThatContainsOtherSignal(List<String> containingSignals, String targetSignal) {
    for (String signal : containingSignals) {
      boolean match = true;
      for (int i=0;i<targetSignal.length();i++) {
        if (!signal.contains(targetSignal.charAt(i) + "")) {
          match = false;
          break;
        }
      }

      if (match) {
        return signal;
      }
    }
    return null;
  }

  private static String sortSignal(String signal) {
    char[] temp = signal.toCharArray();
    Arrays.sort(temp);
    return new String(temp);
  }
}
