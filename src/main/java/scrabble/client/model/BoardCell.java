package scrabble.client.model;

public class BoardCell {
        private Tile tile;
        private String multiplier; // "DW", "TW", "DL", "TL", null
        private int row;
        private int col;

        public BoardCell(int row, int col, String multiplier) {
            this.row = row;
            this.col = col;
            this.multiplier = multiplier;
        }

        public Tile getTile() { return tile; }
        public void setTile(Tile tile) { this.tile = tile; }
        public boolean hasTile() { return tile != null; }
        public String getMultiplier() { return multiplier; }
        public int getRow() { return row; }
        public int getCol() { return col; }
    }