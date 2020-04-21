package de.rkasper.rkbongoapp.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.rkasper.rkbongoapp.R;
import de.rkasper.rkbongoapp.model.BongoNote;
import de.rkasper.rkbongoapp.testdata.TestData;

/**
 * Zeigt {@link de.rkasper.rkbongoapp.model.BongoNote}s
 * im Detail an. Und leiter die Crud Operation ein.
 */
public class BongoNoteCrudActivity extends AppCompatActivity {
	//region 0. Konstanten
	//endregion
	
	//region 1. Decl. and Init Attribute
	private TextView txtvEditDate;
	private TextView txtvLongitude;
	private TextView txtvLatitude;
	private TextView txtvAltitude;
	private TextView txtvBongoPictureDescription;
	
	private EditText txtBongoNoteName;
	private EditText txtBongoNoteContent;
	
	private ImageView imgvBongoPicture;
	//endregion
	
	//region 2. Lebenszyklus
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//1. Layout setzen
		setContentView(R.layout.bongo_note_crud_activity_layout);
		
		//2. Widgets generieren
		this.txtvEditDate = this.findViewById(R.id.txtvEditDate);
		this.txtvLongitude = this.findViewById(R.id.txtvLongitude);
		this.txtvLatitude = this.findViewById(R.id.txtvLatitude);
		this.txtvAltitude = this.findViewById(R.id.txtvAltitude);
		this.txtvBongoPictureDescription = this.findViewById(R.id.txtvBongoPictureDescription);
		
		this.txtBongoNoteName = this.findViewById(R.id.txtBongoNoteName);
		this.txtBongoNoteContent = this.findViewById(R.id.txtBongoNoteContent);
		
		this.imgvBongoPicture = this.findViewById(R.id.imgvBongoPicture);
		
		//3. Evtl. Daten setzen
		this.checkForExtrasAndShowBongoNoteDataOnGui();
		
		//4. Listener generieren und zuweisen
	}
	//endregion
	
	//region 3. Menu
	
	/**
	 * Generiert das Menu
	 *
	 * @param menu : {@link Menu} : Menu was generiert werden soll
	 *
	 * @return true zum anzeigen des Menus false um es nicht anzuzeigen
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.bongo_note_crud_menu, menu);
		return true;
	}
	
	/**
	 * Zum auswerten welches MenuItem geklickt wurde.
	 *
	 * @param mnuItem : {@link MenuItem} : Geklicktes menu item
	 *
	 * @return true : Richtiges Klickhandling
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem mnuItem) {
		Toast.makeText(this, mnuItem.getTitle(), Toast.LENGTH_SHORT).show();
		return true;
	}
	
	//endregion
	
	//region Hilfsmethoden und Funktionen
	
	/**
	 * TODO Checkt ob Extras beim startenden Intent
	 * voliegen. Gibt es keine Extras wurde diese Activity
	 * im {@link de.rkasper.rkbongoapp.logic.MainActivityListener#onMenuItemClick(MenuItem)}
	 * gestartet. Liegene Extras vor so wurde diese Actvity im
	 * {@link de.rkasper.rkbongoapp.logic.MainActivityListener#onItemClick(AdapterView, View, int, long)}
	 * gestartet.
	 * <p>
	 * Liegen Extras vor so werden die Daten der passenden
	 * {@link BongoNote} auf der Gui angezeigt.
	 * <p>
	 * Liegen keine Extras vor geschieht nichts.
	 */
	private void checkForExtrasAndShowBongoNoteDataOnGui() {
		
		//1. Intent beschaffen welches die Activity gestartet hat.
		Intent intentStartBongoNoteCrudActivity = this.getIntent();
		
		//2. ExtraName aus res/values/strings.xml ausles
		String strExtraNameId = this.getString(R.string.strExtraNameId);
		
		//3. Checken ob ein Extra voliegt
		if (intentStartBongoNoteCrudActivity.hasExtra(strExtraNameId)) {
			
			//4. Extras beschaffen
			Bundle allExtras = intentStartBongoNoteCrudActivity.getExtras();
			
			//5. Id auslesen
			int iId = allExtras.getInt(strExtraNameId);
			
			//TODO 6. Bongo mit uebergebener Id auslesen moementan Testadatenliste spaeter DB aufruf
			List<BongoNote> testBongoNoteList = TestData.getTestBongoNoteList();
			
			//Gesuchte Notiz decl.
			BongoNote searchedBongoNote = null;
			
			//In Liste alle Elemente untersuchen ob eines die uebergebene Id hat.
			for (BongoNote bn : testBongoNoteList) {
				if (bn.getId() == iId) {
					//Gesuchte BongoNote merken
					searchedBongoNote = bn;
				}
			}
			
			//Checken ob die Notiz tatsaelich gefunden wurde
			if (searchedBongoNote != null) {
				//Daten in die Gui eintragen
				this.txtvEditDate.setText(searchedBongoNote.getEditDate());
				
				//TODO Location spaeter machen
//				this.txtvLongitude.setText(searchedBongoNote.get);
//				this.txtvLatitude.setText(searchedBongoNote.get);
//				this.txtvAltitude.setText(searchedBongoNote.get);
				
				//TODO Bild spaeter machen
//				this.txtvBongoPictureDescription.setText(searchedBongoNote.get);
				
				this.txtBongoNoteName.setText(searchedBongoNote.getName());
				this.txtBongoNoteContent.setText(searchedBongoNote.getNoteContent());

//				this.imgvBongoPicture.setText(searchedBongoNote.get);
			}
		}
	}
	//endregion
}
