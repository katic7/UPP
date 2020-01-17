import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KreirajCasopisComponent } from './kreiraj-casopis.component';

describe('KreirajCasopisComponent', () => {
  let component: KreirajCasopisComponent;
  let fixture: ComponentFixture<KreirajCasopisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KreirajCasopisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KreirajCasopisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
