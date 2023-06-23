package Client.Controllers.MainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainPageController {

    @FXML
    private MenuItem accountPageButton;

    @FXML
    private ImageView addPlaylistButton;

    @FXML
    private Button createPlaylistButton;

    @FXML
    private Button homePageButton;

    @FXML
    private MenuItem logoutButton;

    @FXML
    private Button lyricsButton;

    @FXML
    private Button nextButton;

    @FXML
    private ImageView nextButtonIcon;

    @FXML
    private ScrollPane pageHolder;

    @FXML
    private Button playStopButton;

    @FXML
    private ImageView playStopButtonIcon;

    @FXML
    private Button playlistPageButton;

    @FXML
    private VBox playlistVbox;

    @FXML
    private Button previousButton;

    @FXML
    private ImageView previousButtonIcon;

    @FXML
    private MenuItem profilePageButton;

    @FXML
    private ImageView profilePictureView;

    @FXML
    private Button searchPageButton;

    @FXML
    private Button shuffleButton;

    @FXML
    private ImageView shuffleButtonIcon;

    @FXML
    void createPlaylist(ActionEvent event) {

    }

    @FXML
    void goAccountPage(ActionEvent event) {

    }

    @FXML
    void goHomePage(ActionEvent event) {

    }

    @FXML
    void goLyricsPage(ActionEvent event) {

    }

    @FXML
    void goNextTrack(ActionEvent event) {

    }

    @FXML
    void goPlaylistPage(ActionEvent event) {

    }

    @FXML
    void goPreviousTrack(ActionEvent event) {

    }

    @FXML
    void goProfilePage(ActionEvent event) {

    }

    @FXML
    void goSearchPage(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void playStopTrack(ActionEvent event) {

    }

    @FXML
    void shufflePlaylist(ActionEvent event) {

    }
    /*
        setter and getters
     */

    public MenuItem getAccountPageButton() {
        return accountPageButton;
    }

    public void setAccountPageButton(MenuItem accountPageButton) {
        this.accountPageButton = accountPageButton;
    }

    public ImageView getAddPlaylistButton() {
        return addPlaylistButton;
    }

    public void setAddPlaylistButton(ImageView addPlaylistButton) {
        this.addPlaylistButton = addPlaylistButton;
    }

    public Button getCreatePlaylistButton() {
        return createPlaylistButton;
    }

    public void setCreatePlaylistButton(Button createPlaylistButton) {
        this.createPlaylistButton = createPlaylistButton;
    }

    public Button getHomePageButton() {
        return homePageButton;
    }

    public void setHomePageButton(Button homePageButton) {
        this.homePageButton = homePageButton;
    }

    public MenuItem getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(MenuItem logoutButton) {
        this.logoutButton = logoutButton;
    }

    public Button getLyricsButton() {
        return lyricsButton;
    }

    public void setLyricsButton(Button lyricsButton) {
        this.lyricsButton = lyricsButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public void setNextButton(Button nextButton) {
        this.nextButton = nextButton;
    }

    public ImageView getNextButtonIcon() {
        return nextButtonIcon;
    }

    public void setNextButtonIcon(ImageView nextButtonIcon) {
        this.nextButtonIcon = nextButtonIcon;
    }

    public ScrollPane getPageHolder() {
        return pageHolder;
    }

    public void setPageHolder(ScrollPane pageHolder) {
        this.pageHolder = pageHolder;
    }

    public Button getPlayStopButton() {
        return playStopButton;
    }

    public void setPlayStopButton(Button playStopButton) {
        this.playStopButton = playStopButton;
    }

    public ImageView getPlayStopButtonIcon() {
        return playStopButtonIcon;
    }

    public void setPlayStopButtonIcon(ImageView playStopButtonIcon) {
        this.playStopButtonIcon = playStopButtonIcon;
    }

    public Button getPlaylistPageButton() {
        return playlistPageButton;
    }

    public void setPlaylistPageButton(Button playlistPageButton) {
        this.playlistPageButton = playlistPageButton;
    }

    public VBox getPlaylistVbox() {
        return playlistVbox;
    }

    public void setPlaylistVbox(VBox playlistVbox) {
        this.playlistVbox = playlistVbox;
    }

    public Button getPreviousButton() {
        return previousButton;
    }

    public void setPreviousButton(Button previousButton) {
        this.previousButton = previousButton;
    }

    public ImageView getPreviousButtonIcon() {
        return previousButtonIcon;
    }

    public void setPreviousButtonIcon(ImageView previousButtonIcon) {
        this.previousButtonIcon = previousButtonIcon;
    }

    public MenuItem getProfilePageButton() {
        return profilePageButton;
    }

    public void setProfilePageButton(MenuItem profilePageButton) {
        this.profilePageButton = profilePageButton;
    }

    public ImageView getProfilePictureView() {
        return profilePictureView;
    }

    public void setProfilePictureView(ImageView profilePictureView) {
        this.profilePictureView = profilePictureView;
    }

    public Button getSearchPageButton() {
        return searchPageButton;
    }

    public void setSearchPageButton(Button searchPageButton) {
        this.searchPageButton = searchPageButton;
    }

    public Button getShuffleButton() {
        return shuffleButton;
    }

    public void setShuffleButton(Button shuffleButton) {
        this.shuffleButton = shuffleButton;
    }

    public ImageView getShuffleButtonIcon() {
        return shuffleButtonIcon;
    }

    public void setShuffleButtonIcon(ImageView shuffleButtonIcon) {
        this.shuffleButtonIcon = shuffleButtonIcon;
    }
}