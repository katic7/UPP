import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifikujProfilComponent } from './verifikuj-profil.component';

describe('VerifikujProfilComponent', () => {
  let component: VerifikujProfilComponent;
  let fixture: ComponentFixture<VerifikujProfilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerifikujProfilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifikujProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
